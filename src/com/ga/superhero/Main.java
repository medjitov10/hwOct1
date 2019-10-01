package com.ga.superhero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Superhero> superheroes = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			readFile("input.txt");
		} catch (IOException e) {
			System.out.println("Can't read a file");
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is your name? ");
		String name = scanner.nextLine();

		try {
			writeFile("output.txt", name, render());
		} catch (IOException e) {
			System.out.println("Error while writing into the file: " + e.getMessage());
		}
	}

	private static String render() {
		System.out.println("Let's play a game.");
		int points = 0;

		Scanner scanner = new Scanner(System.in);

		for (Superhero hero: superheroes) {
			System.out.println("What is " + hero.getName() + "'s real name?" );
			String name = scanner.nextLine();
			if (hero.getRealName().equalsIgnoreCase(name)) {
				System.out.println("You are correct!!! This is " + hero.getName() + "'s name");
				points++;
			} else {
				System.out.println("Are you kidding?");
				points--;
			}

			System.out.println("What is "+ hero.getName() + "'s power?");
			String power = scanner.nextLine();

			if (hero.getPower().equalsIgnoreCase(power)) {
				System.out.println("Correct!!! This is exactly what he can do");
				points++;
			} else {
				System.out.println("This is not " + hero.getName() + "'s power");
				points--;
			}
			System.out.println("What universe " + hero.getName() + " from?");
			String universe = scanner.nextLine();
			if (hero.getUniverse().equalsIgnoreCase(universe)) {
				System.out.println("You are right!");
				points++;
			} else {
				System.out.println("This is not " + hero.getName() + "'s power");
				points--;
			}


		}
		if (points > 0) {
			System.out.println("WOOOOO! You just won!!!!");
			return "won";
		} else {
			System.out.println("Unfortunately, you lost =(");
			return "lost";
		}
	}

	public static void writeFile(String fileName, String name, String result) throws IOException {
		File file = new File(fileName);
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(name+","+result);
		}
		finally {
			writer.close();
		}
	}

	public static void readFile(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));

			String currentLine = reader.readLine();

			while(currentLine != null) {
				String[] data = currentLine.split(",");
				Superhero superhero = new Superhero();
				superhero.setName(data[0]);
				superhero.setPower(data[1]);
				superhero.setUniverse(data[2]);
				superhero.setRealName(data[3]);
				superheroes.add(superhero);
				currentLine = reader.readLine();
			}
		} finally {
			reader.close();
		}
	}

}
