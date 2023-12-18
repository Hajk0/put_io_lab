package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import javax.swing.tree.ExpandVetoException;

public class ExpenseRepositoryTest {
    ExpenseRepository expenseRepository = null;

    @BeforeEach
    public void setUp() {
        //IFancyDatabase mockObject = mock(IFancyDatabase.class);
        //IFancyDatabase myDatabase = new MyDatabase();
        //this.expenseRepository = new ExpenseRepository(myDatabase);

    }

    @Test
    public void loadExpenses() {
        IFancyDatabase database = mock(IFancyDatabase.class);

        when(database.queryAll()).thenReturn(Collections.emptyList());

        this.expenseRepository = new ExpenseRepository(database);
        this.expenseRepository.loadExpenses();

        //verify(database).connect();
        //verify(database).queryAll();
        //verify(database).close();
        InOrder inOrder = inOrder(database);

        inOrder.verify(database).connect();
        inOrder.verify(database).queryAll();
        inOrder.verify(database).close();

        assertEquals(this.expenseRepository.getExpenses(), new ArrayList<Expense>());

    }

    @Test
    public void saveExpanses() {
        IFancyDatabase database = mock(IFancyDatabase.class);

        when(database.queryAll()).thenReturn(Collections.emptyList());

        this.expenseRepository = new ExpenseRepository(database);
        for (int i = 0; i < 5; i++) {
            this.expenseRepository.loadExpenses();
            this.expenseRepository.addExpense(new Expense());
            this.expenseRepository.saveExpenses();
        }

        InOrder inOrder = inOrder(database);

        inOrder.verify(database).connect();
        inOrder.verify(database, times(5)).persist(any());
        inOrder.verify(database).close();

        //assertEquals(this.expenseRepository.getExpenses(), new ArrayList<Expense>());
    }
}
