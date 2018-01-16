package tests.greglturnquist.payroll;

import com.greglturnquist.payroll.emails.EmailParser;
import com.greglturnquist.payroll.emails.EmailSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Paulina on 16.01.2018.
 */
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
        assertThatCode(() -> emailSender.sendSelfEmail(emailParser))
                .doesNotThrowAnyException();
    }

}
