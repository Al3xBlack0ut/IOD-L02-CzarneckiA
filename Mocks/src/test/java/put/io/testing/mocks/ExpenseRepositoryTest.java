package put.io.testing.mocks;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    @Test
    void loadExpenses() {
        // Create mock object based on IFancyDatabase interface
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        
        // Configure mock to return empty list when queryAll is called
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());
        
        // Create repository with mock database and call loadExpenses
        ExpenseRepository repository = new ExpenseRepository(mockDatabase);
        repository.loadExpenses();
        
        // Verify interaction order: connect -> queryAll -> close
        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();
        
        // Assert that the expenses list is empty
        assertTrue(repository.getExpenses().isEmpty());
    }

    @Test
    void saveExpenses() {
        // Create mock object based on IFancyDatabase interface
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        
        // Create repository with mock database
        ExpenseRepository repository = new ExpenseRepository(mockDatabase);
        
        // Add 5 expenses to repository
        for (int i = 0; i < 5; i++) {
            Expense expense = new Expense();
            repository.addExpense(expense);
        }
        
        // Call saveExpenses
        repository.saveExpenses();
        
        // Verify that persist was called 5 times with any Expense object
        verify(mockDatabase, times(5)).persist(any(Expense.class));
    }
}
