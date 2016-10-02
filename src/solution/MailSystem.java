package solution;

import java.util.logging.Logger;

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
	        // ����� ������ ��� ��������� ������� �������� �����.
	        return mail;
	    }
	}
	// ������������� ���������� � ���������� RuntimeException
	public static class StolenPackageException extends RuntimeException {
	}


	public static class IllegalPackageException extends RuntimeException {
	}


	public static class UntrustworthyMailWorker implements MailService {
	    // ���������� ��������� RealMailService ����� ������� ����� � ����,
	    // ��� ��, ��������, � ������������.
	    private static final MailService realService = new RealMailService();
	    private final MailService[] agents;

	    public UntrustworthyMailWorker(final MailService[] agents) {
	        this.agents = agents;
	    }

	    public MailService getRealMailService() {
	        return realService;
	    }

	    @Override
	    public Sendable processMail(Sendable mail) {
	        for (MailService agent : agents) {
	            mail = agent.processMail(mail);
	        }
	        return realService.processMail(mail);
	    }
	}


	public static class Spy implements MailService {
	    private final Logger logger;

	    public Spy(final Logger logger) {
	        this.logger = logger;
	    }

	    @Override
	    public Sendable processMail(Sendable mail) {
	        if (mail instanceof MailMessage) {
	            String direction = "from " + mail.getFrom() + " to " + mail.getTo();
	            if (isTargetMail(mail)) {
	                // ����� ��� �� ������� �������� �� ����� ������� � ���������� �����������.
	                logger.warning(
	                        "Detected target mail correspondence: "
	                        + direction + " \"" + ((MailMessage) mail).getMessage() + "\"");
	            } else {
	                logger.info("Usual correspondence: " + direction);
	            }
	        }
	        return mail;
	    }

	    private boolean isTargetMail(Sendable mail) {
	        // ���������� ������� �� ��������� ����� ����� ����� ����� �������,
	        // ������� �� ����� �������� null.
	        // ��� �������� �������� ����������� NullPointerException.
	        // ���� ��� ������� ����� ���� null, ����� ������ Objects.equals
	        return AUSTIN_POWERS.equals(mail.getFrom()) || AUSTIN_POWERS.equals(mail.getTo());
	    }
	}


	public static class Inspector implements MailService {

	    private static final String[] ILLEGAL_CONTENT =
	            new String[]{WEAPONS, BANNED_SUBSTANCE};

	    @Override
	    public Sendable processMail(Sendable mail) {
	        if (mail instanceof MailPackage) {
	            MailPackage mailPackage = (MailPackage) mail;
	            if (mailPackage.getContent().getContent().contains("stones")) {
	                throw new StolenPackageException();
	            }
	            for (String illegalString : ILLEGAL_CONTENT) {
	                if (mailPackage.getContent().getContent().contains(illegalString)) {
	                    throw new IllegalPackageException();
	                }
	            }
	        }
	        return mail;
	    }
	}


	public static class Thief implements MailService {
	    private final int minValueToSteal;
	    private int stolenValue = 0;

	    public Thief(int minValueToSteal) {
	        this.minValueToSteal = minValueToSteal;
	    }

	    public int getStolenValue() {
	        return stolenValue;
	    }

	    @Override
	    public Sendable processMail(Sendable mail) {
	        if (mail instanceof MailPackage) {
	            Package content = ((MailPackage) mail).getContent();
	            if (content.getPrice() >= minValueToSteal) {
	                stolenValue += content.getPrice();
	                return new MailPackage(
	                        mail.getFrom(), mail.getTo(), stolenPackage(content));
	            } else
	                return mail;
	        } else {
	            return mail;
	        }
	    }

	    private Package stolenPackage(Package content) {
	        return new Package("stones instead of " + content.getContent(), 0);
	    }
	}
}
