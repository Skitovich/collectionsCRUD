package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IssueRepositoryTest {

    IssueRepository repository = new IssueRepository();
    Issue first = new Issue(1, "Ruslan", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("Rus")), true);
    Issue second = new Issue(2, "Dmitriy", new HashSet<String>(Arrays.asList("Update")), new HashSet<String>(Arrays.asList("Dmitriy")), false);
    Issue third = new Issue(3, "Maksim", new HashSet<String>(Arrays.asList("NewFicha")), new HashSet<String>(Arrays.asList("Maksim")), true);

    @BeforeEach
    void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.findAll();
    }

    @Test
    public void shouldFindByID() {
        Issue expected = third;
        Issue actual = repository.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByID() {
        assertNull(repository.findById(4));
    }


}