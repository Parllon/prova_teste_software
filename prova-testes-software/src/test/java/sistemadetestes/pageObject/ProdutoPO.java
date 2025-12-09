package sistemadetestes.pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object para a página de Cadastro de Produtos
 * Esta classe mapeia todos os elementos da tela de produtos e fornece métodos
 * para interagir com eles durante os testes automatizados
 */
public class ProdutoPO extends BasePO {

    // WebDriverWait para esperas explícitas
    private WebDriverWait wait;

    // ==================== ELEMENTOS DO MODAL DE CADASTRO ====================
    
    /**
     * Campo de input para o código do produto
     */
    @FindBy(id = "codigo")
    public WebElement inputCodigo;
    
    /**
     * Campo de input para o nome do produto
     */
    @FindBy(id = "nome")
    public WebElement inputNome;
    
    /**
     * Campo de input para a quantidade do produto
     */
    @FindBy(id = "quantidade")
    public WebElement inputQuantidade;
    
    /**
     * Campo de input para o valor do produto
     */
    @FindBy(id = "valor")
    public WebElement inputValor;
    
    /**
     * Campo de input para a data de cadastro do produto
     */
    @FindBy(id = "data")
    public WebElement inputData;
    
    // ==================== BOTÕES ====================
    
    /**
     * Botão "Criar" que abre o modal de cadastro de produto
     */
    @FindBy(id = "btn-adicionar")
    public WebElement buttonCriar;
    
    /**
     * Botão "Salvar" dentro do modal de cadastro
     */
    @FindBy(id = "btn-salvar")
    public WebElement buttonSalvar;
    
    /**
     * Botão "Sair" dentro do modal de cadastro
     */
    @FindBy(id = "btn-sair")
    public WebElement buttonSair;
    
    // ==================== MENSAGENS ====================
    
    /**
     * Elemento span que exibe mensagens de erro/validação
     */
    @FindBy(id = "mensagem")
    public WebElement spanMensagem;
    
    /**
     * Div de alerta que contém a mensagem de erro
     */
    @FindBy(css = "#cadastro-produto .alert")
    public WebElement divAlerta;
    
    /**
     * Modal de cadastro de produto
     */
    @FindBy(id = "cadastro-produto")
    public WebElement modalCadastro;
    
    // ==================== TABELA ====================
    
    /**
     * Corpo da tabela onde os produtos são listados
     */
    @FindBy(css = "table tbody")
    public WebElement tabelaCorpo;
    
    /**
     * Primeira coluna (código) da primeira linha da tabela
     */
    @FindBy(css = "table tbody tr:first-child td:nth-child(1)")
    public WebElement primeiraLinhaCodigo;
    
    /**
     * Segunda coluna (nome) da primeira linha da tabela
     */
    @FindBy(css = "table tbody tr:first-child td:nth-child(2)")
    public WebElement primeiraLinhaNome;

    // ==================== CONSTRUTOR ====================
    
    /**
     * Construtor padrão para criação de uma nova instância da página de produtos
     * @param driver Driver do navegador atual
     */
    public ProdutoPO(WebDriver driver) {
        super(driver);
        // Inicializa o WebDriverWait com timeout de 10 segundos
        this.wait = new WebDriverWait(driver, 2);
    }

    // ==================== MÉTODOS AUXILIARES ====================
    
    /**
     * Método para escrever texto em um campo de input
     * Aguarda o elemento estar visível e clicável antes de interagir
     * @param input Elemento de input onde será escrito
     * @param texto Texto a ser escrito no campo
     */
    public void escrever(WebElement input, String texto) {
        // Aguarda o elemento estar clicável
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
        input.sendKeys(texto + Keys.TAB);
    }
    
    /**
     * Obtém a mensagem de erro/validação exibida no modal
     * @return Texto da mensagem exibida
     */
    public String obterMensagem() {
        // Aguarda a mensagem estar visível
        wait.until(ExpectedConditions.visibilityOf(spanMensagem));
        return this.spanMensagem.getText();
    }
    
    /**
     * Obtém o título da página atual
     * @return Título da página
     */
    public String obterTituloDaPagina() {
        return driver.getTitle();
    }
    
    /**
     * Clica no botão "Criar" para abrir o modal de cadastro de produto
     * e aguarda o modal ficar visível
     */
    public void clicarBotaoCriar() {
        // Primeiro clique: registra o evento jQuery
        buttonCriar.click();
        
        // Segundo clique: efetivamente abre o modal
        buttonCriar.click();
        
        // Aguarda o modal ficar visível (classe 'show' adicionada pelo Bootstrap)
        wait.until(ExpectedConditions.attributeContains(modalCadastro, "class", "show"));
        
        // Aguarda o campo código estar clicável (garante que o modal está pronto)
        wait.until(ExpectedConditions.elementToBeClickable(inputCodigo));
    }
    
    /**
     * Clica no botão "Salvar" para tentar salvar o produto
     */
    public void clicarBotaoSalvar() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSalvar));
        buttonSalvar.click();
        
    }
    
    /**
     * Clica no botão "Sair" para fechar o modal
     */
    public void clicarBotaoSair() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSair));
        buttonSair.click();
        buttonSair.click();
        // Aguarda o modal fechar (ficar invisível)
        wait.until(ExpectedConditions.invisibilityOf(modalCadastro));
    }
    
    /**
     * Verifica se existe pelo menos um produto na tabela
     * @return true se existir pelo menos uma linha na tabela, false caso contrário
     */
    public boolean existeProdutoNaTabela() {
        try {
            String html = tabelaCorpo.getAttribute("innerHTML");
            return html.contains("<tr>");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Obtém o código do primeiro produto na tabela
     * @return Código do primeiro produto
     */
    public String obterCodigoPrimeiroProduto() {
        wait.until(ExpectedConditions.visibilityOf(primeiraLinhaCodigo));
        return primeiraLinhaCodigo.getText();
    }
    
    /**
     * Obtém o nome do primeiro produto na tabela
     * @return Nome do primeiro produto
     */
    public String obterNomePrimeiroProduto() {
        wait.until(ExpectedConditions.visibilityOf(primeiraLinhaNome));
        return primeiraLinhaNome.getText();
    }

    // ==================== MÉTODOS DE AÇÃO COMPOSTOS ====================
    
    /**
     * Preenche todos os campos do formulário de cadastro de produto
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param quantidade Quantidade do produto
     * @param valor Valor do produto
     * @param data Data de cadastro do produto (formato: yyyy-MM-dd)
     */
    public void preencherFormulario(String codigo, String nome, String quantidade, String valor, String data) {
        escrever(inputCodigo, codigo);
        escrever(inputNome, nome);
        escrever(inputQuantidade, quantidade);
        escrever(inputValor, valor);
        escrever(inputData, data);
    }
    
    /**
     * Executa a ação completa de cadastrar um produto:
     * 1. Clica no botão Criar
     * 2. Preenche todos os campos
     * 3. Clica no botão Salvar
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param quantidade Quantidade do produto
     * @param valor Valor do produto
     * @param data Data de cadastro do produto
     */
    public void executarAcaoDeCadastrar(String codigo, String nome, String quantidade, String valor, String data) {
        clicarBotaoCriar();
        preencherFormulario(codigo, nome, quantidade, valor, data);
        clicarBotaoSalvar();
    }
}
