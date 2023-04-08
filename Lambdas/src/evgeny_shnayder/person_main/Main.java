package evgeny_shnayder.person_main;

import evgeny_shnayder.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = new ArrayList<>(Arrays.asList(new Person("Ivan", 25),
                new Person("Petr", 38),
                new Person("Ivan", 28),
                new Person("Petr", 32),
                new Person("Roman", 17),
                new Person("Maria", 15),
                new Person("Lilia", 68))
        );

        System.out.println("Список людей: " + personsList);

        /* А. получить список уникальных имен */
        List<String> differentNames = personsList.stream().map(Person::getName).distinct().toList();

        System.out.println("Список людей с уникальными именами: " + differentNames);

        /* Б. вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр. */
        String names = differentNames.stream().collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(names);

        /* В. получить список людей младше 18, посчитать для них средний возраст */

        List<Person> peopleNotAdults = personsList.stream().filter(person -> person.getAge() < 18).toList();
        System.out.println("Список людей моложе 18 лет: " + peopleNotAdults);

        double averageAge = peopleNotAdults
                .stream()
                .mapToDouble(Person::getAge)
                .average()
                .getAsDouble();

        System.out.println("Средний возраст людей моложе 18 лет: " + averageAge);

        /* Г. при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст */

        Map<String, Double> map = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println(map);

        /* Д. получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста */

        List<Person> middleAgedPeople = personsList.stream()
                .filter(age -> age.getAge() > 19 && age.getAge() < 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());

        System.out.println(middleAgedPeople);

        /* Задача 2 */

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество элементов которым нужно вычесть квадратный корень:");

        int elementsCount = scanner.nextInt();

        DoubleStream squareRot = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt).limit(elementsCount);

        squareRot.forEach(System.out::println);
    }
}