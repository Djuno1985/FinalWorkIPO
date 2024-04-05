import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInformation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите следующие данные через пробел(кол-во данных = 6): Фамилия Имя Отчество дата _ рождения номер _ телефона пол)");
        String UserInfo = scanner.nextLine();

        try {
            String[] splitData = UserInfo.split(" ");
            if (splitData.length != 6){
                throw new InputMismatchException("Введено не верное количество данных");
            }

            String surname = splitData[0];
            String name = splitData[1];
            String patronymic = splitData[2];
            String dateBirthString = splitData[3];
            long phoneNumber = Long.parseLong(splitData[4]);
            char gender = splitData[5].charAt(0);

            if (gender != 'ж' && gender != 'м'){
                throw new InputMismatchException("Неверно указан пол: ж-женский и м-мужской");
            }

            LocalDate dateBirth = LocalDate.parse(dateBirthString, DateTimeFormatter.ofPattern("ddMMyyyy"));
            String dataToWrite = String.format("%s %s %s %s %d %c", surname, name, patronymic, dateBirthString, phoneNumber, gender);
            writeToSurnameFile(surname, dataToWrite);
            System.out.println("Данные успешно записаны.");

        }catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка ввода данных: " + e.getMessage());
        }catch (IOException e){
            System.err.println("Ошибка при записи в файл: ");
            e.printStackTrace();
        }finally {
            scanner.close();
        }
    }

    private static void writeToSurnameFile(String surname, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname + ".txt", true))) {
            writer.write(data);
            writer.newLine();
        }
    }
}