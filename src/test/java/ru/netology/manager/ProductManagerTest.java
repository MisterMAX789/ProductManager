package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "firstbook", 1, "me");
    Product book2 = new Book(2, "secondbook", 2, "you");
    Product book3 = new Book(3, "thirdbook", 3, "we");
    Product book4 = new Book(4, "fourthbook", 4, "we");
    Product smart1 = new Smartphone(5, "iphonex", 69500, "Apple");
    Product smart2 = new Smartphone(6, "redmi11", 35000, "Xiaomi");
    Product smart3 = new Smartphone(7, "oneplus", 20000, "BBK");
    Product smart4 = new Smartphone(8, "pocox3", 40000, "Xiaomi");


    @BeforeEach
    public void installation() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smart1);
        manager.add(smart2);
        manager.add(smart3);
        manager.add(smart4);


    }

    @Test
    void SearchByBookTitle() {
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy("secondbook");
        assertArrayEquals(expected, actual);
    }

    @Test
        // Поиск  по имени автора
    void searchBookByAuthorName() {
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("me");
        assertArrayEquals(expected, actual);
    }

    @Test
        // Поиск всех книг определенного автора
    void searchBooksByAuthorName() {
        Product[] expected = new Product[]{book3,book4};
        Product[] actual = manager.searchBy("we");
        assertArrayEquals(expected, actual);
    }

    @Test
        // Поиск книг автора которого в списке нет
    void searchBookByNotUsedAuthor() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("I");
        assertArrayEquals(expected, actual);
    }
    @Test
    void SearchBookWhichNotExist() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Heroes");
        assertArrayEquals(expected, actual);
    }
    @Test // Не используемая модель
    void searchSmartphoneNotUsedModel() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("galaxy");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneModelName() {
        Product[] expected = new Product[]{smart3};
        Product[] actual = manager.searchBy("BBK");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneManufacturer() {
        Product[] expected = new Product[]{smart1};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test // Поиск по названию не используемого производителя
    void searchSmartphoneByNotUsedManufacturer() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Oppo");
        assertArrayEquals(expected, actual);
    }
    @Test // Поиск всех смартфонов производителя
    void searchAllBySmartphoneManufacturer() {
        Product[] expected = new Product[]{smart2,smart4};
        Product[] actual = manager.searchBy("Xiaomi");
        assertArrayEquals(expected, actual);
    }
}