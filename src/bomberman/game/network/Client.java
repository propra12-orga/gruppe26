package bomberman.game.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bomberman.file.FileReader;
import bomberman.game.Level;
import bomberman.game.Settings;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

/**
 * das ist ein Client, der das Netzwerkprotokoll einhält.
 * 
 * @author jessica
 * 
 */
public class Client implements Reader {
	private Scanner in;
	private PrintWriter out;

	/**
	 * Konstruktor, der versucht, mit localhost:9001 zu verbinden. ohne jegliche
	 * Garantien bzgl. Funktionalität.
	 * 
	 */
	public Client() {
		Socket socket = null;
		try {
			// Verbinden mit Server
			socket = new Socket(Settings.server, 9001);
			this.in = new Scanner(socket.getInputStream());
			this.out = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException swallowed) {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void read(final BomberHuman bman, final List<Bomb> bombs) {
		while (!in.hasNext()) {

		}

		for (int i = 0; i < 2; i++) {
			final String line = in.nextLine();
			if (line.startsWith("mv")) {
				String[] split = line.split(" ");
				int posX = Integer.parseInt(split[1]);
				int posY = Integer.parseInt(split[2]);
				bman.setPosX(posX);
				bman.setPosY(posY);
			} else if (line.startsWith("bomb")) {
				String[] split = line.split(" ");
				int flag = Integer.parseInt(split[1]);
				if (flag == 1) {
					bombs.add(new Bomb(bman.getPosX(), bman.getPosY(),
							Settings.TIMERCONSTANT));
				}
			}
		}
		// System.out.println(line);
	}

	@Override
	public void write(final String str) {
		String[] split = str.split("\n");
		for (int i = 0; i < split.length; i++) {
			out.println(split[i]);
		}
		out.flush();
	}

	/**
	 * Implementierung, die das (vom Server geschriebene) Level aus dem Netzwerk
	 * einliest.
	 * 
	 * @return Level Objekt, das das Level enthält
	 * @see bomberman.game.network.Reader#readLevel()
	 */
	@Override
	public Level readLevel() {
		List<String> list = new ArrayList<String>();
		while (!in.hasNextLine()) {

		}
		String tmp = null;
		while (!"poison".equals(tmp)) {
			if (in.hasNext()) {
				tmp = in.nextLine();
				if (!"poison".equals(tmp)) {
					list.add(tmp);
				}
			}
		}

		out.println("ack");
		out.flush();
		FileReader fr = new FileReader(list);
		return fr.parse();
	}
}
