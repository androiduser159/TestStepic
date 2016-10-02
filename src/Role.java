
public class Role {
	
	private String printTextPerRole(String[] roles, String[] textLines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < roles.length; i++) {
			sb.append(roles[i]).append(":").append("\n");
			
			for (int j = 0; j < textLines.length; j++) {
				if(textLines[j].startsWith(roles[i] + ":")) {
					sb.append(j + 1).append(")")
					  .append(textLines[j].replaceFirst(roles[i] + ":", ""))
					  .append("\n");					
				}
			}
			sb.append("\n");
		}
	    return sb.toString();
	}
	
	public static void main(String[] args) {
		
		String[] roles = new String[] {
				"Городничий",
				"Аммос Федорович",
				"Артемий Филиппович",
				"Лука Лукич"
		};

		String[] textLines = new String[] {
				"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
				"Аммос Федорович: Как ревизор?",
				"Артемий Филиппович: Как ревизор?",
				"Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
				"Аммос Федорович: Вот те на!",
				"Артемий Филиппович: Вот не было заботы, так подай!",
				"Лука Лукич: Господи боже! еще и с секретным предписаньем!"
		};
		Role r = new Role();
		String s = r.printTextPerRole(roles, textLines);
		System.out.println(s);
		
	}

}
