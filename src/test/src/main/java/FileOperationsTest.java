package src.main.java;

import com.app.model.DataSummary;
import com.app.service.template.FileOperationsTemplate;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import static com.google.common.collect.Range.greaterThan;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(JUnit4.class)
public class FileOperationsTest {

    @Test
    public void test() throws Exception {
        //Test Read Data
        Try<List<DataSummary>> readResponse = Try.of(() -> new FileOperationsTemplate().read("Input.txt", "20100820"));
        assertThat("Exception occurred", readResponse.isSuccess(), is(Boolean.TRUE)); //No exception occurred
        assertThat("Empty data found", readResponse.get().size(), is(greaterThan(0)));
        List<DataSummary> dataSummaries = readResponse.get();

        //Test Write data
        new FileOperationsTemplate().write(dataSummaries);

        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader("Output.csv"));

        br.readLine(); //header
        line = br.readLine(); //Data begins after the header
        // use comma as separator
        String[] data = line.split(cvsSplitBy);
        //Testing the first record data
        assertThat("Data mismatch in Transaction amount", data[2], is("\"1\"")); //Asserting the Transaction Amount is 1
        assertThat("Data mismatch in Expiration Date", data[1].substring(16, 24), is("20100910")); //Asserting the Expiration date is 20100910
        assertThat("Data mismatch in Client type", data[0].substring(1, 3), is("CL")); //Asserting the Client type is CL
        assertThat("Data mismatch in Client Number", data[0].substring(6, 10), is("4321")); //Asserting the Client Number is 4321
        assertThat("Data mismatch in Account Number", data[0].substring(11, 15), is("0002")); //Asserting the Account Number is 0002
        assertThat("Data mismatch in Sub Account Number", data[0].substring(16, 20), is("0001")); //Asserting the Sub Account Number is 0001

    }
}
