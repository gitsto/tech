package filesystem.iteration6;

public class MyPhysicalDisk implements IPhysicalDisk {

	@Override
	public void createPartition(String unitName, int capacity) {
	}

	@Override
	public IFormat format(ILogicalUnit unit, String format) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
