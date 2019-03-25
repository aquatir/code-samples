package codesample.springboot.reactive.client;

public class SampleDto {
    private Long number;
    private String string;

    public SampleDto() {
    }

    public SampleDto(Long number, String string) {
        this.number = number;
        this.string = string;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "SampleDto{" +
                "number=" + number +
                ", string='" + string + '\'' +
                '}';
    }
}
