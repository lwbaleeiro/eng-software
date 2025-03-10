# SOLID
    SOLID é um acrônimo que representa cinco princípios de design orientado a objetos, formulados para criar sistemas mais compreensíveis, flexíveis e manuteníveis.

## **S - Single Responsibility Principle (Princípio da Responsabilidade Única)**
- **Conceito**: Uma classe deve ter apenas uma razão para mudar, ou seja, deve ter apenas uma responsabilidade.
- **Porque usar**: Quando cada classe tem uma única responsabilidade, o código se torna mais organizado, mais fácil de entender, testar e manter. Classes com múltiplas responsabilidades frequentemente se tornam complexas e difíceis de modificar.
- **Quando usar**: Sempre que perceber que uma classe está realizando mais de uma função ou está crescendo demais, é hora de aplicar este princípio.

```java
// Violando o SRP - Uma classe fazendo muitas coisas
public class UserService {
    private UserRepository userRepository;
    private EmailService emailService;
    
    public User registerUser(UserDto userDto) {
        // Validar os dados do usuário
        if (userDto.getEmail() == null || !userDto.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (userDto.getPassword() == null || userDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }
        
        // Verificar se usuário já existe
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        
        // Salvar o usuário
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(hashPassword(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        
        // Enviar email de boas-vindas
        String subject = "Bem-vindo ao sistema";
        String body = "Olá " + user.getName() + ", obrigado por se registrar!";
        emailService.sendEmail(user.getEmail(), subject, body);
        
        return savedUser;
    }
    
    private String hashPassword(String password) {
        // Lógica para hash de senha
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}

// Aplicando SRP - Dividindo em classes com responsabilidades específicas
public class UserValidator {
    public void validate(UserDto userDto) {
        if (userDto.getEmail() == null || !userDto.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (userDto.getPassword() == null || userDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }
    }
}

public class UserMapper {
    private PasswordEncoder passwordEncoder;
    
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }
}

public class UserRegistrationService {
    private UserRepository userRepository;
    private UserValidator userValidator;
    private UserMapper userMapper;
    private WelcomeEmailSender welcomeEmailSender;
    
    public User registerUser(UserDto userDto) {
        // Validar
        userValidator.validate(userDto);
        
        // Verificar se usuário já existe
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        
        // Converter e salvar
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        
        // Enviar email de boas-vindas
        welcomeEmailSender.sendWelcomeEmail(savedUser);
        
        return savedUser;
    }
}

public class WelcomeEmailSender {
    private EmailService emailService;
    
    public void sendWelcomeEmail(User user) {
        String subject = "Bem-vindo ao sistema";
        String body = "Olá " + user.getName() + ", obrigado por se registrar!";
        emailService.sendEmail(user.getEmail(), subject, body);
    }
}
```

## **O - Open/Closed Principle (Princípio do Aberto/Fechado)**
- **Conceito**: Entidades de software (classes, módulos, funções) devem estar abertas para extensão, mas fechadas para modificação.
- **Porque usar**: Evita que mudanças num componente causem efeitos colaterais em outros. Permite adicionar novos comportamentos sem alterar o código existente, reduzindo riscos e facilitando testes.
- **Quando usar**: Aplicar sempre que você prevê que um comportamento pode variar ou ser estendido no futuro, especialmente em pontos críticos do sistema.

```java
// Violando o OCP
public class PaymentProcessor {
    public void processPayment(Payment payment) {
        if (payment.getType().equals("CREDIT_CARD")) {
            // Lógica de processamento de cartão de crédito
            System.out.println("Processando pagamento com cartão de crédito");
        } else if (payment.getType().equals("PAYPAL")) {
            // Lógica de processamento do PayPal
            System.out.println("Processando pagamento com PayPal");
        } else if (payment.getType().equals("BANK_TRANSFER")) {
            // Lógica de processamento de transferência bancária
            System.out.println("Processando pagamento com transferência bancária");
        }
        // Para adicionar um novo método de pagamento, precisaríamos modificar esta classe
    }
}

// Aplicando OCP com interfaces e polimorfismo
public interface PaymentProcessor {
    void process(Payment payment);
}

@Service
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void process(Payment payment) {
        // Lógica específica para processamento de cartão de crédito
        System.out.println("Processando pagamento com cartão de crédito");
    }
}

@Service
public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void process(Payment payment) {
        // Lógica específica para processamento PayPal
        System.out.println("Processando pagamento com PayPal");
    }
}

@Service
public class BankTransferProcessor implements PaymentProcessor {
    @Override
    public void process(Payment payment) {
        // Lógica específica para processamento de transferência bancária
        System.out.println("Processando pagamento com transferência bancária");
    }
}

// Para adicionar um novo método de pagamento, basta criar uma nova implementação
@Service
public class PixProcessor implements PaymentProcessor {
    @Override
    public void process(Payment payment) {
        // Lógica específica para processamento de Pix
        System.out.println("Processando pagamento com Pix");
    }
}

@Service
public class PaymentService {
    private final Map<String, PaymentProcessor> processors;
    
    // Spring Boot 3 injeta todas as implementações de PaymentProcessor
    public PaymentService(List<PaymentProcessor> processorList) {
        // Mapeia cada processador pelo seu tipo usando Java 17 Pattern Matching for instanceof
        this.processors = processorList.stream()
            .collect(Collectors.toMap(
                processor -> {
                    if (processor instanceof CreditCardProcessor) return "CREDIT_CARD";
                    else if (processor instanceof PayPalProcessor) return "PAYPAL";
                    else if (processor instanceof BankTransferProcessor) return "BANK_TRANSFER";
                    else if (processor instanceof PixProcessor) return "PIX";
                    else return "UNKNOWN";
                },
                processor -> processor
            ));
    }
    
    public void processPayment(Payment payment) {
        PaymentProcessor processor = processors.get(payment.getType());
        if (processor == null) {
            throw new UnsupportedOperationException("Método de pagamento não suportado: " + payment.getType());
        }
        processor.process(payment);
    }
}
```

## **L - Liskov Substitution Principle (Princípio da Substituição de Liskov)**
- Conceito: Objetos de uma classe derivada devem poder substituir objetos da classe base sem afetar a corretude do programa.
- Porque usar: Garante que a herança é usada corretamente, evitando comportamentos inesperados quando subclasses são usadas em vez da classe pai. Isso promove um sistema mais robusto e previsível.
Quando usar: Sempre que estiver definindo hierarquias de classes e relações de herança.

```java
// Violando o LSP
class Retangulo {
    protected int largura;
    protected int altura;
    
    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public int getArea() {
        return largura * altura;
    }
}

class Quadrado extends Retangulo {
    // Um quadrado tem lados iguais, então sobrescrevemos setLargura e setAltura
    @Override
    public void setLargura(int largura) {
        this.largura = largura;
        this.altura = largura;  // Para manter como quadrado
    }
    
    @Override
    public void setAltura(int altura) {
        this.altura = altura;
        this.largura = altura;  // Para manter como quadrado
    }
}

// Este código viola o LSP porque:
void testArea(Retangulo r) {
    r.setLargura(5);
    r.setAltura(4);
    // Esperamos que a área seja 20
    assert r.getArea() == 20; // Falha se r for um Quadrado, pois a área será 16!
}

// Aplicando LSP - Usando interface ou classe abstrata
interface Forma {
    int getArea();
}

class Retangulo implements Forma {
    private int largura;
    private int altura;
    
    public Retangulo(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    @Override
    public int getArea() {
        return largura * altura;
    }
}

class Quadrado implements Forma {
    private int lado;
    
    public Quadrado(int lado) {
        this.lado = lado;
    }
    
    public void setLado(int lado) {
        this.lado = lado;
    }
    
    @Override
    public int getArea() {
        return lado * lado;
    }
}

// Agora no Spring Boot, podemos ter um serviço que trabalha com quaisquer formas
@Service
public class CalculoAreaService {
    public int calcularAreaTotal(List<Forma> formas) {
        return formas.stream()
                .mapToInt(Forma::getArea)
                .sum();
    }
}
```

## **I - Interface Segregation Principle (Princípio da Segregação de Interfaces)**
- **Conceito**: Clientes não devem ser forçados a depender de interfaces que não utilizam. É melhor ter muitas interfaces específicas do que uma interface geral.
- **Porque usar**: Interfaces menores e mais específicas são mais fáceis de implementar, entender e manter. Evita que classes implementem métodos que não precisam.
- **Quando usar**: Quando você perceber que uma interface está crescendo e algumas implementações estão deixando métodos vazios ou lançando exceções.

```java
// Violando o ISP - Interface grande e genérica
interface Trabalhador {
    void trabalhar();
    void comer();
    void dormir();
}

class DesenvolvedorBackend implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Codificando em Java...");
    }
    
    @Override
    public void comer() {
        System.out.println("Comendo enquanto revisa código...");
    }
    
    @Override
    public void dormir() {
        System.out.println("Dormindo com debugger na cabeça...");
    }
}

class Robo implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Executando tarefas automatizadas...");
    }
    
    @Override
    public void comer() {
        // Robôs não comem, método desnecessário 
        throw new UnsupportedOperationException("Robôs não comem");
    }
    
    @Override
    public void dormir() {
        // Robôs não dormem, método desnecessário
        throw new UnsupportedOperationException("Robôs não dormem");
    }
}

// Aplicando ISP - Interfaces menores e específicas
interface Trabalhavel {
    void trabalhar();
}

interface NecessidadesHumanas {
    void comer();
    void dormir();
}

class DesenvolvedorBackend implements Trabalhavel, NecessidadesHumanas {
    @Override
    public void trabalhar() {
        System.out.println("Codificando em Java...");
    }
    
    @Override
    public void comer() {
        System.out.println("Comendo enquanto revisa código...");
    }
    
    @Override
    public void dormir() {
        System.out.println("Dormindo com debugger na cabeça...");
    }
}

class Robo implements Trabalhavel {
    @Override
    public void trabalhar() {
        System.out.println("Executando tarefas automatizadas...");
    }
}

// No Spring Boot, podemos usar interfaces segregadas para definir diferentes tipos de repositórios
public interface ItemReadRepository {
    Optional<Item> findById(Long id);
    List<Item> findAll();
    boolean existsById(Long id);
}

public interface ItemWriteRepository {
    Item save(Item item);
    void deleteById(Long id);
}

// Repositório apenas de leitura para usuários que só precisam consultar
@Repository
public class ItemReadOnlyRepository implements ItemReadRepository {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }
    
    @Override
    public List<Item> findAll() {
        return entityManager.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }
    
    @Override
    public boolean existsById(Long id) {
        return entityManager.createQuery(
            "SELECT COUNT(i) FROM Item i WHERE i.id = :id", Long.class)
            .setParameter("id", id)
            .getSingleResult() > 0;
    }
}

// Repositório completo para serviços que precisam modificar dados
@Repository
public class ItemFullRepository implements ItemReadRepository, ItemWriteRepository {
    @PersistenceContext
    private EntityManager entityManager;
    
    // Implementações de ItemReadRepository...
    
    @Override
    public Item save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
            return item;
        } else {
            return entityManager.merge(item);
        }
    }
    
    @Override
    public void deleteById(Long id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            entityManager.remove(item);
        }
    }
}
```

## **D - Dependency Inversion Principle (Princípio da Inversão de Dependência)**
- **Conceito**: Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações. Além disso, abstrações não devem depender de detalhes, mas detalhes devem depender de abstrações.
- **Porque usar**: Reduz o acoplamento entre componentes do sistema, facilitando testes, manutenção e reuso de código. Permite que partes do sistema sejam substituídas com impacto mínimo.
- **Quando usar**: Sempre que houver dependência entre componentes, especialmente quando um componente de nível superior precisa usar funcionalidades de um componente de nível inferior.

```java
// Violando o DIP - Classe de alto nível depende diretamente de uma implementação
public class NotificationService {
    private EmailSender emailSender = new EmailSender();
    
    public void notifyUser(User user, String message) {
        emailSender.sendEmail(user.getEmail(), "Notificação", message);
    }
}

class EmailSender {
    public void sendEmail(String to, String subject, String body) {
        // Lógica para enviar e-mail
        System.out.println("Enviando e-mail para: " + to);
        System.out.println("Assunto: " + subject);
        System.out.println("Corpo: " + body);
    }
}

// Aplicando DIP com Spring Boot
public interface MessageSender {
    void sendMessage(String to, String subject, String body);
}

@Service
public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String to, String subject, String body) {
        // Lógica para enviar e-mail
        System.out.println("Enviando e-mail para: " + to);
        System.out.println("Assunto: " + subject);
        System.out.println("Corpo: " + body);
    }
}

@Service
public class SMSSender implements MessageSender {
    @Override
    public void sendMessage(String to, String subject, String body) {
        // Lógica para enviar SMS
        System.out.println("Enviando SMS para: " + to);
        System.out.println("Mensagem: " + body);
    }
}

@Service
public class PushNotificationSender implements MessageSender {
    @Override
    public void sendMessage(String to, String subject, String body) {
        // Lógica para enviar notificação push
        System.out.println("Enviando notificação push para: " + to);
        System.out.println("Título: " + subject);
        System.out.println("Conteúdo: " + body);
    }
}

@Service
public class NotificationService {
    private final MessageSender messageSender;
    
    // Injeção de dependência via construtor
    public NotificationService(@Qualifier("emailSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }
    
    public void notifyUser(User user, String message) {
        messageSender.sendMessage(user.getEmail(), "Notificação", message);
    }
}

// Podemos configurar qual implementação usar com base em condições
@Configuration
public class NotificationConfig {
    @Bean
    @ConditionalOnProperty(name = "notification.type", havingValue = "email")
    public MessageSender emailSender() {
        return new EmailSender();
    }
    
    @Bean
    @ConditionalOnProperty(name = "notification.type", havingValue = "sms")
    public MessageSender smsSender() {
        return new SMSSender();
    }
    
    @Bean
    @ConditionalOnProperty(name = "notification.type", havingValue = "push")
    public MessageSender pushSender() {
        return new PushNotificationSender();
    }
}
```