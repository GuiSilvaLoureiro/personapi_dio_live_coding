package one.digitalinnovation.personAPI.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personAPI.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone
{
    @Id // define que esta variavel será a PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gera automaticamente valores para ID
    private Long id;

    @Enumerated(EnumType.STRING) //Define que a entrada de dados é do tipo String
    @Column(nullable = false) //Define que o valor não pode ser nulo, ou seja, é obrigatório
    private PhoneType phoneType;

    @Column(nullable = false)
    private String number;
}
