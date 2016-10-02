
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
				"����������",
				"����� ���������",
				"������� ����������",
				"���� �����"
		};

		String[] textLines = new String[] {
				"����������: � ��������� ���, �������, � ���, ����� �������� ��� ������������� ��������: � ��� ���� �������.",
				"����� ���������: ��� �������?",
				"������� ����������: ��� �������?",
				"����������: ������� �� ����������, ���������. � ��� � ��������� ������������.",
				"����� ���������: ��� �� ��!",
				"������� ����������: ��� �� ���� ������, ��� �����!",
				"���� �����: ������� ����! ��� � � ��������� ������������!"
		};
		Role r = new Role();
		String s = r.printTextPerRole(roles, textLines);
		System.out.println(s);
		
	}

}
