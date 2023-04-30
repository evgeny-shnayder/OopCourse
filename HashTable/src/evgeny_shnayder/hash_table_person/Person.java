package evgeny_shnayder.hash_table_person;

public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int hash = 1;

        hash = prime * hash + name.hashCode();
        hash = prime * hash + Integer.hashCode(age);

        return hash;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}
