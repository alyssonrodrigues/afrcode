/**
 * 
 */
package cursojsf.modelo.entidade.usuario;

/**
 * @author alysson
 *
 */
public enum EnumSexo {
	MASCULINO("Masculino", 0),
	FEMININO("Feminino", 1);
	
	private String descricao;
	private int codigo;
	EnumSexo(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	
	public static EnumSexo getEnum(int codigo) {
		EnumSexo aEnum = null;
		for (EnumSexo umaEnum : EnumSexo.values()) {
			if (codigo == umaEnum.getCodigo()) {
				aEnum = umaEnum;
				break;
			}
		}
		return aEnum;
	}
}
