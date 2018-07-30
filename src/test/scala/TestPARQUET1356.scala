import java.io.File
import java.util

import org.apache.avro.Schema
import org.apache.avro.generic.{GenericRecord, GenericRecordBuilder}
import org.apache.hadoop.fs.Path
import org.apache.parquet.avro.{AvroParquetReader, AvroParquetWriter}
import org.scalatest.FunSuite
import org.apache.hadoop.conf.Configuration

class TestPARQUET1356 extends FunSuite {
  test("PARQUET-1356") {
    val schema = new Schema.Parser().parse("{\n \"type\": \"record\",\n \"name\": \"myrecord\",\n \"fields\": [ {\n \"name\": \"myarray\",\n \"type\": {\n \"type\": \"array\",\n \"items\": \"int\"\n }\n } ]\n}")
    val tmp = File.createTempFile(getClass().getSimpleName(), ".tmp");
    tmp.deleteOnExit();
    tmp.delete();
    val file = new Path(tmp.getPath());
    val testConf = new Configuration();
    val writer = AvroParquetWriter
      .builder[GenericRecord](file)
      .withSchema(schema)
      .withConf(testConf)
      .build();

    // Write a record with an empty array.
    val emptyArray = new util.ArrayList[Integer]();
    val record = new GenericRecordBuilder(schema)
      .set("myarray", emptyArray).build();
    writer.write(record);
    writer.close();

    val reader = new AvroParquetReader[GenericRecord](testConf, file);
    val nextRecord = reader.read()
    print(nextRecord)
  }
}
