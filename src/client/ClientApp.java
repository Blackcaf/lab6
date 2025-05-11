package client;

import common.Request;
import common.Response;
import models.HumanBeing;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8765;

        Scanner scanner = new Scanner(System.in);
        ConsoleImpl console = new ConsoleImpl();
        HumanBeingAsker asker = new HumanBeingAsker(console);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;
            if (input.equalsIgnoreCase("exit")) break;

            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);

            Object payload = null;

            // команды, требующие объект
            if (command.equalsIgnoreCase("add")
                    || command.equalsIgnoreCase("update")
                    || command.equalsIgnoreCase("remove_lower")) {
                payload = asker.askHumanBeing();
            }

            Request request = new Request(command, arguments, payload);

            try (SocketChannel channel = SocketChannel.open(new InetSocketAddress(host, port))) {
                channel.configureBlocking(true);
                Socket socket = channel.socket();

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                out.writeObject(request);
                out.flush();

                Response response = (Response) in.readObject();
                System.out.println(response.getMessage());

                in.close();
                out.close();

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Сервер временно недоступен или произошла ошибка: " + e.getMessage());
            }
        }
    }
}
