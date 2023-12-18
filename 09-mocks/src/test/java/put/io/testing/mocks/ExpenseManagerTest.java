package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {

    @Test
    public void calculateTotal() {
        FancyService fancyService = mock(FancyService.class);
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);

        Expense e1 = new Expense();
        Expense e2 = new Expense();
        Expense e3 = new Expense();
        e1.setAmount(10);
        e2.setAmount(20);
        e3.setAmount(30);

        when(expenseRepository.getExpenses()).thenReturn(Arrays.asList(e1, e2, e3));

        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, fancyService);
        long result = expenseManager.calculateTotal();

        assertEquals(result, 60);
    }

    @Test
    public void calculateTotalForCategory() {
        FancyService fancyService = mock(FancyService.class);
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);

        Expense e1 = new Expense();
        Expense e2 = new Expense();
        Expense e3 = new Expense();
        e1.setAmount(10);
        e2.setAmount(20);
        e3.setAmount(30);
        e1.setCategory("Home");
        e2.setCategory("Home");
        e3.setCategory("Car");

        when(expenseRepository.getExpensesByCategory("Home")).thenReturn(Arrays.asList(e1, e2));
        when(expenseRepository.getExpensesByCategory("Car")).thenReturn(Arrays.asList(e3));
        when(expenseRepository.getExpensesByCategory("Food")).thenReturn(Collections.emptyList());
        when(expenseRepository.getExpensesByCategory("Sport")).thenReturn(Collections.emptyList());

        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, fancyService);

        assertEquals(0, expenseManager.calculateTotalForCategory("Food"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Sport"));
    }

    @Test
    public void calculateTotalInDolars() throws ConnectException {
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
        FancyService fancyService = mock(FancyService.class);

        Expense e1 = new Expense();
        Expense e2 = new Expense();
        Expense e3 = new Expense();
        e1.setAmount(10);
        e2.setAmount(20);
        e3.setAmount(30);

        when(fancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        double arg = invocation.getArgument(0);
                        return arg * 4.0;
                    }
                }
        );
        when(expenseRepository.getExpenses()).thenReturn(Arrays.asList(e1, e2, e3));

        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, fancyService);

        long res = expenseManager.calculateTotalInDollars();

        assertEquals(res, 60 * 4.0);
    }
}
