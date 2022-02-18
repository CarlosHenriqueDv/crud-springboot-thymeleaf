# Aplicação Web com Spring Boot / Thymeleaf e outras tecnologias.

# Índice 
+ [Tecnologias Utilizadas](#tecnologias-utilizadas)
    - [Back-End](#back-end)
    - [Front-End](#front-end)
    - [Ferramentas](#ferramentas)
+ [Código Fonte](#código-fonte)  
   - [Padrão MVC](#padrão-MVC)
   - [Versionamento com Git](#versionamento-com-git)
   - [Java com Springboot](#java-com-springboot)
   - [Java Persistence API com Hibernate](#java-persistence-api-com-hibernate)    
+ [Rodando a Aplicação](#Rodando-a-Aplicação)
    
## Tecnologias Utilizadas
    
### Back-End 
| Tecnologia | Descrição |
| ------ | ------ |
| Java 8 | Linguagem de programação orientada a objetos |
| SpringBoot | Spring Boot é um projeto da Spring que veio para facilitar o processo de configuração e publicação de nossas aplicações. Você escolhe os módulos que deseja através dos starters que inclui no pom.xml do seu projeto. Eles, basicamente, são dependências que agrupam outras dependências. |
| Spring MVC | O framework Spring, é um dos frameworks Java mais conhecido e utilizado. Esse framework implementa um grande número de funções, como injeção de dependência, persistência de dados e uma implementação para o padrão MVC para a criação de aplicações WEB|
| Hibernate | Hibernate é o framework para persistência de dados mais utilizado em projetos Java. Sendo uma das primeiras opções a implementar o conceito de mapeamento objeto-relacional (ORM), em pouco tempo se tornou referência entre os desenvolvedores, tendo influenciado, inclusive, a criação da especificação JPA.|
| JPA | JPA é a especificação do Java que dita como os frameworks ORM devem ser implementados. Ela foi criada com o intuito de padronizar essas soluções. Antes de sua criação existiam diversos frameworks e bibliotecas que abstraiam os desafios da persistência com ORM em Java.|
| MySQL | É um sistema de gerenciamento de banco de dados, que utiliza a linguagem SQL como interface. |
    
### Front-End 
| Tecnologia | Descrição |
| ------ | ------ 
| Thymeleaf | Thymeleaf é um mecanismo de modelo Java XML / XHTML / HTML5 que pode funcionar tanto em ambientes web como fora da web. É mais adequado para servir XHTML / HTML5 na camada de visualização de aplicativos da web baseados em MVC, mas pode processar qualquer arquivo XML mesmo em ambientes off-line. |
| Javascript | JavaScript é uma linguagem de programação interpretada estruturada, de script em alto nível com tipagem dinâmica fraca e multi-paradigma. Juntamente com HTML e CSS, o JavaScript é uma das três principais tecnologias da World Wide Web. |
| Jquery | jQuery é uma biblioteca de funções JavaScript que interage com o HTML, desenvolvida para simplificar os scripts interpretados no navegador do cliente. |
| Bootstrap | Bootstrap é um framework web com código-fonte aberto para desenvolvimento de componentes de interface e front-end para sites e aplicações web usando HTML, CSS e JavaScript, baseado em modelos de design para a tipografia, melhorando a experiência do usuário em um site amigável e responsivo. |
### Ferramentas
| Ferramenta | Descrição |
| ------ | ------ |
| Intellij IDEA | O IntelliJ IDEA é um ambiente de desenvolvimento integrado escrito em Java para o desenvolvimento de software. |
| Mysql Workbench | É uma ferramenta visual de design de banco de dados que integra desenvolvimento, administração, design, criação e manutenção de SQL em um único ambiente de desenvolvimento integrado para o sistema de banco de dados MySQL. |

## Código Fonte
Abaixo alguns dos principais trechos de código da aplicação com comentários mostrando a necessidade e/ou objetivo da implementação.

### Padrão MVC
Foi utilizado o padrão de projetos *MVC* - **Model View Controller**.

>MVC é nada mais que um padrão de arquitetura de software, separando sua aplicação em 3 camadas.
>A camada de interação do usuário(view), a camada de manipulação dos dados(model) e a camada de controle(controller).

### Versionamento Com Git
Plataforma em nuvem para armazenar o código-fonte foi o [Github](https://github.com/).

### Java com SpringBoot 
A anotação *@Controller* transforma a classe em um *bean* do Spring e faz com ela seja reconhecida pelo framework como camada de controller, existem várias outras anotações importantes que foram utilizadas dessa implementação, como @RequestMapping que define o *path* de acesso desse controller, @GetMapping, @PutMapping e todos os outros métodos *Http's*.

Outra anotação muito utilizada nesta aplicação é a *@Autowired* que define uma injeção de depedência.

~~~java
@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;

    private enum RESOURCES{
        CADASTRO_CARGO("cargo/cadastro"), LISTA_CARGO("cargo/lista");

        RESOURCES(String path) {
            this.path = path;
        }

        private String path;

    }

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo) {
        return RESOURCES.CADASTRO_CARGO.path;
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
                         @RequestParam("page")Optional<Integer> page,
                         @RequestParam("dir")Optional<String> dir) {

        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");

        PaginacaoUtil<Cargo> pageCargo = cargoService.buscaPorPaginada(paginaAtual, ordem);

        model.addAttribute("pageCargo", pageCargo);
        return RESOURCES.LISTA_CARGO.path;
    }
}    
~~~
### Java Persistence API com Hibernate 
Foi utilizado também *JPA* como modelagem para as *entidades*

~~~java
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

    @NotBlank(message = "O nome do cargo é obrigatório.")
    @Size(max = 60, message = "O nome do cargo deve conter no máximo 60 caracteres.")
    @Column(name = "nome", nullable = false, unique = true, length = 60)
    private String nome;

    @NotNull(message = "Selecione o departamento desse cargo.")
    @ManyToOne
    @JoinColumn(name = "id_departamento_fk")
    private Departamento departamento;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

~~~  

Para acesso a Banco de Dados foi utilizado o padrão DAO *Data Access Object*. Nesse contexto foi usada a anotação *@Repository* nas classes DAO específicas e cada uma extende a classe *AbstractDao* com todos os métodos de **CRUD** ja carregados.

### Thymeleaf

>O Thymeleaf não é um projeto Spring, mas uma biblioteca que foi criada para facilitar a criação da camada de view com uma forte >integração com o Spring, e uma boa alternativa ao JSP.

>O principal objetivo do Thymeleaf é prover uma forma elegante e bem formatada para criarmos nossas páginas. O dialeto do Thymeleaf é >bem poderoso como você verá no desenvolvimento da aplicação, mas você também pode estendê-lopara customizar de acordo com suas necessidades

Foi utilizado o thymeleaf para fazer a parte da transferencia de objeto entre back e front end, também foi utilizado o recurso de fragmentação do thymeleaf que otimiza o uso de código html evitando repetição de código

### Rodando a Aplicação
A aplicação pode ser iniciada rodando o simples comando no terminal *mvn spring-boot:run*.

