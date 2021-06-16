package one.digitalinnovation.personAPI.dto.request;

import lombok.*;
import one.digitalinnovation.personAPI.enums.PhoneType;
import org.hibernate.annotations.BatchSize;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO
{
    private Long id;

    @Enumerated(EnumType.STRING) //Aqui estou garantindo como o dado deve ser cadastrado
    private PhoneType phoneType;

    @NotEmpty
    @Size(min = 13, max = 14)
    private String number;
}
