package pl.put.poznan.transformer.model;

/**
 * Klasa do wysłania jsona z odpowiedzią
 * Zwraca po prostu przekształcony text
 */
public class TransformResponse {
    private String transformedText;

    public TransformResponse() {
    }

    public TransformResponse(String transformedText) {
        this.transformedText = transformedText;
    }

    public String getTransformedText() {
        return transformedText;
    }

    public void setTransformedText(String transformedText) {
        this.transformedText = transformedText;
    }

    @Override
    public String toString() {
        return "TransformResponse{" +
                "transformedText='" + transformedText + '\'' +
                '}';
    }
}
