package messing.around.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import bomberman.game.Settings;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

public class Server implements Reader {
	public static void main(final String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9001);

		Socket client = server.accept();
		final Scanner in;
		try {
			in = new Scanner(client.getInputStream());
			PrintWriter out = new PrintWriter(client.getOutputStream());

			while (in.hasNext()) {
				if (!in.hasNext()) {
					continue;
				}

				final String line = in.nextLine();
				if (line.equals("hello")) {
					out.println("good day");
					out.flush();
					System.out
							.println("client said hello, I think he likes me");
				} else {
					out.println("unknown");
					out.flush();
				}

			}
		} catch (IOException e) {

		}

	}

	final Scanner in;
	final PrintWriter out;

	public Server() throws IOException {
		ServerSocket server = new ServerSocket(9001);

		Socket client = server.accept();
		final Scanner in;
		in = new Scanner(client.getInputStream());
		this.in = in;
		PrintWriter out = new PrintWriter(client.getOutputStream());
		this.out = out;
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
}
