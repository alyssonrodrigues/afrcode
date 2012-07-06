package cursojsf.fronteira.action;

import org.hibernate.validator.Length;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

@Name("acaoHelloWorld")
public class AcaoHelloWorld extends Acao
{
    @Logger private Log log;

    @In StatusMessages statusMessages;

    private String nome;

    public void sayHello()
    {
        log.info("HelloWorld.sayHello() action called with: " +
        		"#{acaoHelloWorld.nome}");
        statusMessages.add("Hello #{acaoHelloWorld.nome}!");
    }

    @Length(max = 10)
    public String getNome()
    {
        return nome;
    }
  
    public void setNome(String value)
    {
        this.nome = value;
    }
  
}
