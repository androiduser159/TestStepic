/*
 * ����� �������, ����������� ������ �������������� �������� �������.
 * 
 */
import java.util.logging.*;

public class MailSystem {

	public static final String AUSTIN_POWERS = "Austin Powers";
	public static final String WEAPONS = "weapons";
	public static final String BANNED_SUBSTANCE = "banned substance";
	
	/*
	���������: ��������, ������� ����� ��������� �� �����.
	� ����� �������� ����� �������� �� ���� � ���� ������������ ������.
	*/
	public static interface Sendable {
	    String getFrom();
	    String getTo();
	}
	
	/*
	���������, ������� ������ �����, ������� ����� �����-���� ������� ���������� �������� ������.
	*/
	public static interface MailService {
	    Sendable processMail(Sendable mail);
	}
	
	/*
	����������� �����,������� ��������� �������������� ������ ��������
	��������� � ���������� ������ � ��������������� ����� ������.
	*/
	public static abstract class AbstractSendable implements Sendable {

	    protected final String from;
	    protected final String to;

	    public AbstractSendable(String from, String to) {
	        this.from = from;
	        this.to = to;
	    }

	    @Override
	    public String getFrom() {
	        return from;
	    }

	    @Override
	    public String getTo() {
	        return to;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        AbstractSendable that = (AbstractSendable) o;

	        if (!from.equals(that.from)) return false;
	        if (!to.equals(that.to)) return false;

	        return true;
	    }

	}
	
	/*
	������, � �������� ���� �����, ������� ����� �������� � ������� ������ `getMessage`
	*/
	public static class MailMessage extends AbstractSendable {

	    private final String message;

	    public MailMessage(String from, String to, String message) {
	        super(from, to);
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        if (!super.equals(o)) return false;

	        MailMessage that = (MailMessage) o;

	        if (message != null ? !message.equals(that.message) : that.message != null) return false;

	        return true;
	    }

	}
	
	/*
	�������, ���������� ������� ����� �������� � ������� ������ `getContent`
	*/
	public static class MailPackage extends AbstractSendable {
	    private final Package content;

	    public MailPackage(String from, String to, Package content) {
	        super(from, to);
	        this.content = content;
	    }

	    public Package getContent() {
	        return content;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        if (!super.equals(o)) return false;

	        MailPackage that = (MailPackage) o;

	        if (!content.equals(that.content)) return false;

	        return true;
	    }

	}
	
	/*
	�����, ������� ������ �������. � ������� ���� ��������� �������� ����������� � ������������� ��������.
	*/
	public static class Package {
	    private final String content;
	    private final int price;

	    public Package(String content, int price) {
	        this.content = content;
	        this.price = price;
	    }

	    public String getContent() {
	        return content;
	    }

	    public int getPrice() {
	        return price;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Package aPackage = (Package) o;

	        if (price != aPackage.price) return false;
	        if (!content.equals(aPackage.content)) return false;

	        return true;
	    }
	}
	
	/*
	�����, � ������� ������ ������ ��������� �����
	*/
	public static class RealMailService implements MailService {

	    @Override
	    public Sendable processMail(Sendable mail) {
	        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	        System.out.println(((MailPackage) mail).getContent().getContent() 
	        		+ " " + ((MailPackage) mail).getContent().getPrice());
	        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	        return mail;
	    }
	}
	
	/*
	 * �����, ������������ ����������� ��������� �����, ������� ������ ����, 
	 * ����� �������� �������� ������ ��������������� � ������ �����, 
	 * ��������������� �������� ���� ������ ������ ������� ���, � �����, 
	 * � ����� ������, �������� ������������ ������ ��������������� ���������� 
	 * RealMailService.
	 */
	public static class UntrustworthyMailWorker implements MailService{
		private MailService[] mailService;
		private RealMailService realMailService = new RealMailService();
		
		public UntrustworthyMailWorker(MailService[] mailService) {
			this.mailService = mailService;
		}
		
		public RealMailService getRealMailService() {			
			return realMailService;
		}
		
		@Override
		public Sendable processMail(Sendable mail) {
			for (int i = 0; i < mailService.length; i++) {
				mail = mailService[i].processMail(mail);
				System.out.println("service: " + mailService[i]);
			}
			mail = realMailService.processMail(mail);
			return mail;
		}
		
		
	}
	
	/*
	 * �����, ������� ��������� � ���� �������� ���������, ������� �������� 
	 * ����� ��� ����. ������ �������������� �� ���������� Logger, 
	 * � ������� �������� ����� ����� �������� � ���� ���������. 
	 * �� ������ ������ �� ��������� ������ MailMessage � ����� � ������ 
	 * ��������� ���������:
	 * 2.1) ���� � �������� ����������� ��� ���������� ������ "Austin Powers", 
	 * �� ����� �������� � ��� ��������� � ������� WARN: 
	 * Detected target mail correspondence: from {from} to {to} "{message}"
	 * 2.2) �����, ���������� �������� � ��� ��������� � ������� INFO: 
	 * Usual correspondence: from {from} to {to}
	 */
	public static class Spy implements MailService{
		private Logger LOGGER;//Logger.getLogger(Spy.class.getName());

		public Spy(Logger LOGGER) {
			this.LOGGER = LOGGER;
		}
		@Override
		public Sendable processMail(Sendable mail) {
			if (mail instanceof MailMessage) {
				if (mail.getFrom().equals(AUSTIN_POWERS) || 
					mail.getTo().equals(AUSTIN_POWERS)) {
					LOGGER.log(Level.WARNING, 
							"Detected target mail correspondence: from {0} to {1} \"{2}\"",
							new Object[]{mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
				}
				else {
					LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}",
							new Object[]{mail.getFrom(), mail.getTo()});
				}
			}
			return mail;
		}
		
	}
	
	/*
	 * Thief � ���, ������� ������ ����� ������ ������� � ���������� ��� ���������.
	 */
	public static class Thief implements MailService{
		private int minPrice;
		private int stolenValue;
		
		public Thief(int minPrice) {
			this.minPrice = minPrice;
		}
		
		public int getStolenValue() {
			return stolenValue;
			
		}

		@Override
		public Sendable processMail(Sendable mail) {
			if (mail instanceof MailPackage) {
				
				int pricePackage = ((MailPackage) mail).getContent().getPrice();
				if (pricePackage >= minPrice) {
					stolenValue += pricePackage;
					String content = ((MailPackage) mail).getContent().getContent();
					mail = new MailPackage(mail.getFrom(), mail.getTo(), 
						   new Package("stones instead of " + content, 0));
				}
			}
			return mail;
		}
		
	}
	
	/*
	 * Inspector � ���������, ������� ������ �� ������������ � ����������� 
	 * ��������� � ���� ������� � ���� ����������, ���� ���� ���������� 
	 * �������� �������.
	 */
	public static class Inspector implements MailService {

		@Override
		public Sendable processMail(Sendable mail) {
			if (mail instanceof MailPackage) {
				String content = ((MailPackage) mail).getContent().getContent();
				if (content.contains(WEAPONS)
					|| content.contains(BANNED_SUBSTANCE)) {
					throw new IllegalPackageException();
				}
				if (content.contains("stones")) {
					throw new StolenPackageException();					
				}
			}
			return mail;
		}
		
	}
	
	public static class IllegalPackageException extends RuntimeException {

	}
	
	public static class StolenPackageException extends RuntimeException {

	}
	
	public static void main(String[] args) {
		
		Spy spy = new Spy(Logger.getLogger(Spy.class.getName()));
		Thief thief = new Thief(5);
		Inspector inspector = new Inspector();
		MailService[] mailService = {spy, thief, inspector};
		UntrustworthyMailWorker uwmWorker = new UntrustworthyMailWorker(mailService);
		MailPackage mp = new MailPackage(AUSTIN_POWERS, "komuto", new Package("gold", 4));
		//mp = (MailPackage) thief.processMail(mp);
		//System.out.println(mp.getContent().getContent() + " " +  mp.getContent().getPrice());
		mp = (MailPackage) uwmWorker.processMail(mp);
		//System.out.println(uwmWorker.processMail(mp));
		System.out.println(thief.getStolenValue());
		System.out.println(mp.getContent().getContent() + " " +  mp.getContent().getPrice());
		
	}
}
