package sistemadetestes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import sistemadetestes.pageObject.LoginPO;
import sistemadetestes.pageObject.ProdutoPO;

/**
 * Classe de testes automatizados para a funcionalidade de Cadastro de Produtos
 * 
 * Esta classe implementa 3 dos 5 casos de teste planejados na planilha:
 * - TC001: Não deve cadastrar produto com todos os campos vazios
 * - TC002: Não deve cadastrar produto com apenas código preenchido
 * - TC005: Deve cadastrar produto com todos os campos preenchidos corretamente
 * 
 * @author Aluno
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest extends BaseTest {
    
    // Page Objects utilizados nos testes
    private static LoginPO loginPage;
    private static ProdutoPO produtoPage;
    
    /**
     * Método de preparação executado antes de todos os testes
     * Realiza o login no sistema e inicializa os Page Objects
     */
    @BeforeClass
    public static void prepararTestes() {
        // Inicializa o Page Object de Login
        loginPage = new LoginPO(driver);
        
        // Realiza o login com credenciais válidas para acessar a página de produtos
        loginPage.executarAcaoDeLogar("admin@admin.com", "admin@123");
        
        // Aguarda a navegação para a página de produtos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Inicializa o Page Object de Produtos após o login
        produtoPage = new ProdutoPO(driver);
    }
    
    /**
     * TC001 - Não deve cadastrar produto com todos os campos vazios
     * 
     * Cenário: Usuário tenta cadastrar um produto sem preencher nenhum campo
     * Resultado Esperado: Sistema exibe mensagem "Todos os campos são obrigatórios para o cadastro!"
     * 
     * Campos testados:
     * - Código: vazio
     * - Nome: vazio
     * - Quantidade: vazio
     * - Valor: vazio
     * - Data: vazio
     */
    @Test
    public void TC001_naoDeveCadastrarProdutoComTodosOsCamposVazios() {
        // Abre o modal de cadastro e tenta salvar com todos os campos vazios
        produtoPage.executarAcaoDeCadastrar("", "", "", "", "");
        
        // Obtém a mensagem de erro exibida
        String mensagem = produtoPage.obterMensagem();
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals("Todos os campos são obrigatórios para o cadastro!", mensagem);
        
        // Fecha o modal para o próximo teste
        produtoPage.clicarBotaoSair();
        
        // Aguarda o modal fechar
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * TC002 - Não deve cadastrar produto com apenas código preenchido
     * 
     * Cenário: Usuário tenta cadastrar um produto preenchendo apenas o código
     * Resultado Esperado: Sistema exibe mensagem "Todos os campos são obrigatórios para o cadastro!"
     * 
     * Campos testados:
     * - Código: "001"
     * - Nome: vazio
     * - Quantidade: vazio
     * - Valor: vazio
     * - Data: vazio
     */
    @Test
    public void TC002_naoDeveCadastrarProdutoComApenasCodigoPreenchido() {
        // Abre o modal de cadastro e tenta salvar apenas com o código preenchido
        produtoPage.executarAcaoDeCadastrar("001", "", "", "", "");
        
        // Obtém a mensagem de erro exibida
        String mensagem = produtoPage.obterMensagem();
        
        // Verifica se a mensagem de erro é a esperada
        assertEquals("Todos os campos são obrigatórios para o cadastro!", mensagem);
        
        // Fecha o modal para o próximo teste
        produtoPage.clicarBotaoSair();
        
        // Aguarda o modal fechar
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * TC005 - Deve cadastrar produto com todos os campos preenchidos corretamente
     * 
     * Cenário: Usuário cadastra um produto preenchendo todos os campos corretamente
     * Resultado Esperado: Produto é cadastrado com sucesso e exibido na tabela
     * 
     * Campos testados:
     * - Código: "004"
     * - Nome: "Mouse Gamer"
     * - Quantidade: "50"
     * - Valor: "150.00"
     * - Data: "2024-03-10"
     */
    @Test
    public void TC005_deveCadastrarProdutoComTodosOsCamposPreenchidos() {
        // Define os dados do produto a ser cadastrado
        String codigo = "004";
        String nome = "Mouse Gamer";
        String quantidade = "50";
        String valor = "150.00";
        String data = "2024-03-10";
        
        // Executa a ação de cadastrar o produto
        produtoPage.executarAcaoDeCadastrar(codigo, nome, quantidade, valor, data);
        
        // Aguarda o processamento
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verifica se o produto foi adicionado na tabela
        assertTrue("Produto deveria estar na tabela após cadastro", 
                   produtoPage.existeProdutoNaTabela());
        
        // Verifica se os dados do produto cadastrado estão corretos
        assertEquals("Código do produto não confere", codigo, produtoPage.obterCodigoPrimeiroProduto());
        assertEquals("Nome do produto não confere", nome, produtoPage.obterNomePrimeiroProduto());
    }
}
