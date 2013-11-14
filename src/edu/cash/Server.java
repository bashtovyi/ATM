package edu.cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends ATM {
	public static void main(String[] args) throws IOException {

		try {
			ServerSocket serverSocket = new ServerSocket(23);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				PrintWriter printWriter = new PrintWriter(
						clientSocket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));

				String line = "";
				boolean isAuthorised = true;
				printWriter.println();
				List<CurrencyBox> currencyBoxList = new ArrayList<>();
				while ((line = bufferedReader.readLine()) != null) {
					String[] argumentsArray = line.split(" ");
					if (!isAuthorised) {
						if (argumentsArray.length == 4) {
							if (argumentsArray[0].equals("-l")
									&& argumentsArray[2].equals("-p")
									&& argumentsArray[1].equals("user")
									&& argumentsArray[3].equals("pass")) {
								isAuthorised = true;
								printWriter.println("WELCOME, "
										+ argumentsArray[1]);
							} else {
								break;
							}
						} else {
							break;
						}
					}

					if (line.equals("EXIT")) {
						break;
					}

					if (isAddCash(argumentsArray)) {
						addCash(argumentsArray, currencyBoxList);
						printWriter.println("OK");
					} else if (isWithDraw(argumentsArray)) {
						whithDraw(argumentsArray, currencyBoxList);
						printWriter.println("OK");
					} else if (line.equals("?")) {
						printBalance(currencyBoxList, printWriter);
					} else {
						printWriter.println("ERROR");
					}
				}
				printWriter.println("BYE");

				printWriter.close();
				bufferedReader.close();
			}
		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ 23 + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

	public static void whithDraw(String[] argumentsArray,
			List<CurrencyBox> currencyBoxList) {
		String currency = argumentsArray[1];
		int amount = Integer.valueOf(argumentsArray[2]);
		int nominal;
		int count;
		

	}
}
