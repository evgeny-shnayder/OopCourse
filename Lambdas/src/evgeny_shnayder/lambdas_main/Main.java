package evgeny_shnayder.lambdas_main;

import evgeny_shnayder.lambdas_person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Ivan", 25),
                new Person("Petr", 38),
                new Person("Ivan", 28),
                new Person("Petr", 32),
                new Person("Roman", 11),
                new Person("Maria", 17),
                new Person("Lilia", 68));

        personsList.get(0).setAge(24);
        personsList.get(0).setName("Roman");

        System.out.println("Список людей: " + personsList);

        /* А. получить список уникальных имен */
        List<String> uniqueNames = personsList.stream()
                .map(Person::getName)
                .distinct()
                .toList();

        System.out.println("Список людей с уникальными именами: " + uniqueNames);

        /* Б. вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр. */
        String listNames = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("Список людей с уникальными именами. " + listNames);

        /* В. получить список людей младше 18, посчитать для них средний возраст */

        List<Person> notAdultsPeoples = personsList.stream()
                .filter(person -> person.getAge() < 18)
                .toList();
        System.out.println("Список людей моложе 18 лет: " + notAdultsPeoples);

        OptionalDouble averageAge = notAdultsPeoples.stream()
                .mapToInt(Person::getAge)
                .average();

        System.out.println("Средний возраст людей моложе 18 лет: " + averageAge);

        /* Г. при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст */

        Map<String, Double> averageAgeByNames = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Средний возраст людей с группировкой по именам: " + averageAgeByNames);

        /* Д. получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста */

        List<Person> middleAgedPeople = personsList.stream()
                .filter(age -> age.getAge() >= 20 && age.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .toList();

        System.out.println("Люди, возраст которых от 20 до 45 лет: " + middleAgedPeople);

        /* Задача 2 */

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество элементов которым нужно вычислить квадратный корень:");
        int elementsCount = scanner.nextInt();

        DoubleStream squareRoots = DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(elementsCount);

        squareRoots.forEach(System.out::println);
    }
}
