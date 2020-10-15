package apap.tutorial.haidokter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "apotek")
public class ApotekModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_apotek;

    @NotNull
    @Size(max = 10)
    @Column(name = "nama_apotek", nullable = false)
    private String nama_apotek;

    @NotNull
    @Size(max = 50)
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 5)
    @Column(name = "kode_pos", nullable = true)
    private Integer kode_pos;

    @OneToMany(mappedBy = "apotekModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObatModel> listObat;

}
