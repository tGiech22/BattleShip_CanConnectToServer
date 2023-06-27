package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa04.model.json.message.ReportDamageJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for ReportDamageJson
 */
public class ReportDamageJsonTest {
  /**
   * testing ReportDamageJson
   */
  @Test
  public void testReportDamageJson() {
    ReportDamageJson reportDamageJson =
        new ReportDamageJson("report-damage", null);

    assertEquals(reportDamageJson.methodName(), "report-damage");
    assertNull(reportDamageJson.arguments());
  }
}

