package messing.around.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import messing.around.file.FileReader;
import bomberman.game.Level;
import bomberman.game.Settings;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

public class Client implements Reader {
	public static void main(final String[] args) {
		final Socket socket;

		try {
			socket = new Socket("localhost", 9001);

			final Scanner in = new Scanner(socket.getInputStream());
			final PrintWriter writeOut = new PrintWriter(
					socket.getOutputStream());

			for (int i = 0; i < 2; i++) {
				writeOut.println("hello");
				writeOut.flush();

				System.out.println(in.nextLine());

			}

			Thread.sleep(20000);
			writeOut.println("still there?");
			writeOut.flush();

			System.out.println(in.nextLine());

			socket.close();
		} catch (Exception e) {

		}

	}

	Scanner in;
	PrintWriter out;

	public Client() {
		final Socket socket;
		try {
			// Verbinden mit Server
			socket = new Socket("localhost", 9001);
			this.in = new Scanner(socket.getInputStream());
			this.out = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("2");
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Override
	public Level readLevel() {
		List<String> list = new ArrayList<String>();
		while (!in.hasNext()) {
			list.add(in.nextLine());
		}
		out.println("ack");
		out.flush();
		FileReader fr = new FileReader(list);
		return fr.parse();
	}
}
