package put.io.testing.mocks;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {

    @Test
    void calculateTotal() {
        // Create mock objects
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);
        
        // Create 3 expenses
        Expense expense1 = new Expense();
        expense1.setAmount(100);
        
        Expense expense2 = new Expense();
        expense2.setAmount(200);
        
        Expense expense3 = new Expense();
        expense3.setAmount(300);
        
        List<Expense> expenses = Arrays.asList(expense1, expense2, expense3);
        
        // Configure mock to return the list of expenses
        when(mockRepository.getExpenses()).thenReturn(expenses);
        
        // Create manager with mock dependencies and call calculateTotal
        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);
        long result = manager.calculateTotal();
        
        // Assert that the total is correct (100 + 200 + 300 = 600)
        assertEquals(600, result);
    }

    @Test
    void calculateTotalForCategory() {
        // Create mock objects
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);
        
        // Create expenses for "Home" category
        Expense homeExpense1 = new Expense();
        homeExpense1.setAmount(100);
        homeExpense1.setCategory("Home");
        
        Expense homeExpense2 = new Expense();
        homeExpense2.setAmount(200);
        homeExpense2.setCategory("Home");
        
        List<Expense> homeExpenses = Arrays.asList(homeExpense1, homeExpense2);
        
        // Create expenses for "Car" category
        Expense carExpense1 = new Expense();
        carExpense1.setAmount(300);
        carExpense1.setCategory("Car");
        
        Expense carExpense2 = new Expense();
        carExpense2.setAmount(50);
        carExpense2.setCategory("Car");
        
        List<Expense> carExpenses = Arrays.asList(carExpense1, carExpense2);
        
        // Configure mock: general matcher FIRST, then specific categories
        when(mockRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(mockRepository.getExpensesByCategory("Home")).thenReturn(homeExpenses);
        when(mockRepository.getExpensesByCategory("Car")).thenReturn(carExpenses);
        
        // Create manager with mock dependencies
        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);
        
        // Test all four categories
        assertEquals(300, manager.calculateTotalForCategory("Home"));  // 100 + 200
        assertEquals(350, manager.calculateTotalForCategory("Car"));   // 300 + 50
        assertEquals(0, manager.calculateTotalForCategory("Food"));    // empty
        assertEquals(0, manager.calculateTotalForCategory("Sport"));   // empty
    }

    @Test
    void calculateTotalInDollars() throws ConnectException {
        // Create mock repository and mock service
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);
        
        // Create expenses with total of 1000 PLN
        Expense expense1 = new Expense();
        expense1.setAmount(500);
        
        Expense expense2 = new Expense();
        expense2.setAmount(500);
        
        List<Expense> expenses = Arrays.asList(expense1, expense2);
        
        // Configure mock repository to return expenses
        when(mockRepository.getExpenses()).thenReturn(expenses);
        
        // Configure mock service with dynamic calculation: 1 USD = 4 PLN
        // First parameter (amount in PLN) divided by 4 = amount in USD
        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD")))
            .thenAnswer(invocation -> {
                double amount = invocation.getArgument(0);
                return amount / 4.0;
            });
        
        // Create manager with mocks
        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);
        
        // Call method that converts to dollars
        long result = manager.calculateTotalInDollars();
        
        // Assert the result is 250 USD (1000 PLN / 4)
        assertEquals(250, result);
    }

    @Test
    void calculateTotalInDollarsWithException() throws ConnectException {
        // Create mock repository and mock service
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);
        
        // Create expenses
        Expense expense1 = new Expense();
        expense1.setAmount(500);
        
        List<Expense> expenses = Arrays.asList(expense1);
        
        // Configure mock repository
        when(mockRepository.getExpenses()).thenReturn(expenses);
        
        // Configure mock service to throw exception
        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD")))
            .thenThrow(new ConnectException("Connection failed"));
        
        // Create manager with mocks
        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);
        
        // Call method - should handle exception and return -1
        long result = manager.calculateTotalInDollars();
        
        // Assert that exception was handled and -1 returned
        assertEquals(-1, result);
    }

}
