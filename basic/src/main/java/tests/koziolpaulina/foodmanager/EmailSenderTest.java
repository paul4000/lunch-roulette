package tests.koziolpaulina.foodmanager;

import com.koziolpaulina.foodmanager.emails.EmailParser;
import com.koziolpaulina.foodmanager.emails.EmailSender;
import com.koziolpaulina.foodmanager.emails.ShoppingListSender;
import com.koziolpaulina.foodmanager.recipes.Ingredient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailSenderTest {

    private EmailSender emailSender;

    @Mock
    private EmailParser emailParser = mock(EmailParser.class);

    @Test
    void should_successfully_send_email(){

        //given

        when(emailParser.getEmailTitle()).thenReturn("Sample subject");
        when(emailParser.getEmailBody()).thenReturn("Sample email body");

        //when
        emailSender = new EmailSender();

        //then
        assertThatCode(() -> emailSender.sendEmail("pk.koziol49@gmail.com", new ShoppingListSender("Test lunch", Arrays.asList(new Ingredient("blybly", 3), new Ingredient("gagaga", 4)))))
                .doesNotThrowAnyException();
    }

}
