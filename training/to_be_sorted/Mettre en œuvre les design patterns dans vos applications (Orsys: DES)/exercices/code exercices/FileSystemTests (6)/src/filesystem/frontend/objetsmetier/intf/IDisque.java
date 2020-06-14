package filesystem.frontend.objetsmetier.intf;

public interface IDisque {

	String getDernierNomAffecte();

	IUniteLogique creerUniteLogique(IPartition partition);

	void format(IUniteLogique uniteLogique, String format);

}
