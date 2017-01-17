package ua.pp.lazin.javajet.validation;

/**
 * The type Object error.
 *
 * @author Ruslan Lazin
 */
public class ObjectError {

    private String basicMessage;

    private String bundleKey;

    /**
     * Instantiates a new Object error.
     *
     * @param basicMessage the basic message
     * @param bundleKey    the bundle key
     */
    public ObjectError(String basicMessage, String bundleKey) {
        this.basicMessage = basicMessage;
        this.bundleKey = bundleKey;
    }

    /**
     * Gets basic message.
     *
     * @return the basic message
     */
    public String getBasicMessage() {
        return basicMessage;
    }

    /**
     * Sets basic message.
     *
     * @param basicMessage the basic message
     */
    public void setBasicMessage(String basicMessage) {
        this.basicMessage = basicMessage;
    }

    /**
     * Gets bundle key.
     *
     * @return the bundle key
     */
    public String getBundleKey() {
        return bundleKey;
    }

    /**
     * Sets bundle key.
     *
     * @param bundleKey the bundle key
     */
    public void setBundleKey(String bundleKey) {
        this.bundleKey = bundleKey;
    }
}
