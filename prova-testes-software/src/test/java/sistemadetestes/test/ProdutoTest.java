package sistemadetestes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sistemadetestes.pageObject.LoginPO;
import sistemadetestes.pageObject.ProdutoPO;

/**
 * Classe de testes automatizados para a funcionalidade de Cadastro de Produtos
 * 
 * Esta classe implementa 10 casos de teste planejados na planilha para testar
 * todas as combinações de campos vazios e preenchidos no formulário de cadastro.
 * 
 * @author Parllon e Gabriel
 * @version 2.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest extends BaseTest {
    
    // Page Objects utilizados nos testes
    private static LoginPO loginPage;
    private static ProdutoPO produtoPage;
    private static WebDriverWait wait;
    
    // Mensagem de erro esperada para campos obrigatórios
    private static final String MSG_CAMPOS_OBRIGATORIOS = "Todos os campos são obrigatórios para o cadastro!";
    
    /**
     * Método de preparação executado antes de todos os testes
     * Realiza o login no sistema e inicializa os Page Objects
     */
    @BeforeClass
    public static void prepararTestes() {
        // Inicializa o WebDriverWait
        wait = new WebDriverWait(driver, 10);
        
        // Inicializa o Page Object de Login
        loginPage = new LoginPO(driver);
        
        // Realiza o login com credenciais válidas para acessar a página de produtos
        loginPage.executarAcaoDeLogar("admin@admin.com", "admin@123");
        
        // Aguarda a página de produtos carregar (verifica pelo título)
        wait.until(ExpectedConditions.titleContains("Controle de Produtos"));
        
        // Inicializa o Page Object de Produtos após o login
        produtoPage = new ProdutoPO(driver);
    }
    
    /**
     * Método executado antes de cada teste
     * Atualiza a página (F5) para garantir estado limpo
     */
    @Before
    public void antesDeCadaTeste() {
        driver.navigate().refresh();
        // Aguarda a página recarregar e o botão Criar estar clicável
        wait.until(ExpectedConditions.elementToBeClickable(produtoPage.buttonCriar));
    }
    
    /**
     * TC001 - Não deve cadastrar produto com todos os campos vazios
     * 
     * Cenário: Usuário tenta cadastrar um produto sem preencher nenhum campo
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC001_naoDeveCadastrarProdutoComTodosOsCamposVazios() {
        // Executa ação de cadastrar com todos os campos vazios
        produtoPage.executarAcaoDeCadastrar("", "", "", "", "");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC002 - Não deve cadastrar produto com apenas código preenchido
     * 
     * Cenário: Usuário preenche apenas o campo código
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC002_naoDeveCadastrarProdutoComApenasCodigo() {
        // Executa ação de cadastrar com apenas código preenchido
        produtoPage.executarAcaoDeCadastrar("001", "", "", "", "");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC003 - Não deve cadastrar produto com apenas nome preenchido
     * 
     * Cenário: Usuário preenche apenas o campo nome
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC003_naoDeveCadastrarProdutoComApenasNome() {
        // Executa ação de cadastrar com apenas nome preenchido
        produtoPage.executarAcaoDeCadastrar("", "Teclado", "", "", "");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC004 - Não deve cadastrar produto com apenas quantidade preenchida
     * 
     * Cenário: Usuário preenche apenas o campo quantidade
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC004_naoDeveCadastrarProdutoComApenasQuantidade() {
        // Executa ação de cadastrar com apenas quantidade preenchida
        produtoPage.executarAcaoDeCadastrar("", "", "100", "", "");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC005 - Não deve cadastrar produto com apenas valor preenchido
     * 
     * Cenário: Usuário preenche apenas o campo valor
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC005_naoDeveCadastrarProdutoComApenasValor() {
        // Executa ação de cadastrar com apenas valor preenchido
        produtoPage.executarAcaoDeCadastrar("", "", "", "250.00", "");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC006 - Não deve cadastrar produto com apenas data preenchida
     * 
     * Cenário: Usuário preenche apenas o campo data
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC006_naoDeveCadastrarProdutoComApenasData() {
        // Executa ação de cadastrar com apenas data preenchida
        produtoPage.executarAcaoDeCadastrar("", "", "", "", "2024-05-20");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC007 - Não deve cadastrar produto sem código (demais campos preenchidos)
     * 
     * Cenário: Usuário preenche todos os campos exceto o código
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC007_naoDeveCadastrarProdutoSemCodigo() {
        // Executa ação de cadastrar sem o código
        produtoPage.executarAcaoDeCadastrar("", "Monitor LED", "30", "899.90", "2024-06-15");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC008 - Não deve cadastrar produto sem nome (demais campos preenchidos)
     * 
     * Cenário: Usuário preenche todos os campos exceto o nome
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC008_naoDeveCadastrarProdutoSemNome() {
        // Executa ação de cadastrar sem o nome
        produtoPage.executarAcaoDeCadastrar("005", "", "25", "450.00", "2024-07-10");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC009 - Não deve cadastrar produto sem data (demais campos preenchidos)
     * 
     * Cenário: Usuário preenche todos os campos exceto a data
     * Resultado Esperado: Sistema exibe mensagem de campos obrigatórios
     */
    @Test
    public void TC009_naoDeveCadastrarProdutoSemData() {
        // Executa ação de cadastrar sem a data
        produtoPage.executarAcaoDeCadastrar("006", "Webcam HD", "40", "189.90", "");
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals(MSG_CAMPOS_OBRIGATORIOS, produtoPage.obterMensagem());
        
        // Fecha o modal
        produtoPage.clicarBotaoSair();
    }
    
    /**
     * TC010 - Deve cadastrar produto com todos os campos preenchidos corretamente
     * 
     * Cenário: Usuário preenche todos os campos corretamente
     * Resultado Esperado: Produto é cadastrado com sucesso e exibido na tabela
     */
    @Test
    public void TC010_deveCadastrarProdutoComTodosOsCamposPreenchidos() {
        // Define os dados do produto a ser cadastrado
        String codigo = "007";
        String nome = "Mouse Gamer";
        String quantidade = "50";
        String valor = "150.00";
        String data = "2024-03-10";
        
        // Executa a ação de cadastrar o produto
        produtoPage.executarAcaoDeCadastrar("007", "Mouse Gamer", "50", "150", "10-03-2024");
        
        // Verifica se o produto foi adicionado na tabela
        assertTrue("Produto deveria estar na tabela após cadastro", 
                   produtoPage.existeProdutoNaTabela());
        
        // Verifica se os dados do produto cadastrado estão corretos
        assertEquals("Código do produto não confere", codigo, produtoPage.obterCodigoPrimeiroProduto());
        assertEquals("Nome do produto não confere", nome, produtoPage.obterNomePrimeiroProduto());
    }
}
