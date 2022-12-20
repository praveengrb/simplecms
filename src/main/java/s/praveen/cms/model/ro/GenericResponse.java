package s.praveen.cms.model.ro;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * The type Generic response.
 *
 * @param <K> the type parameter
 */
@With
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class GenericResponse<K> {
    /**
     * The Status.
     */
    HttpStatus status;
    /**
     * The Result.
     */
    K result;
    /**
     * The Message.
     */
    String message;

    /**
     * Instantiates a new Generic response.
     *
     * @param status the status
     * @param result the result
     */
    public GenericResponse(HttpStatus status, K result) {
        this.status = status;
        this.result = result;
    }
}
