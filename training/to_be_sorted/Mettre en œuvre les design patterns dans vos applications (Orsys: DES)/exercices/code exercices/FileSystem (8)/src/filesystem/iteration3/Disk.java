package filesystem.iteration3;

import java.util.HashMap;
import java.util.Map;

public class Disk implements IDisk {
	private static IDisk disk;
	private IPhysicalDisk physicalDisk;
	private int capacity;
	private char driveName='A';
	private Map<String, ILogicalUnit> logicalUnits = new HashMap<String, ILogicalUnit>();
	
	private Disk(IPhysicalDisk physicalDisk) {
		this.physicalDisk = physicalDisk;
	}

	public static IDisk getInstance() {
		if (disk == null) {
			// How the actual physical disk is obtained should be encapsulated
			disk = new Disk(new MyPhysicalDisk());
		}
		return disk;
	}

	@Override
	public IPhysicalDisk getPhysicalDisk() {
		return physicalDisk;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public ILogicalUnit createUnit(IPartition partition) {
		String unitName = "" + driveName;
		physicalDisk.createUnit(unitName, partition);
		ILogicalUnit unit = new LogicalUnit(unitName, partition);
		logicalUnits.put(unitName, unit);
		driveName++;
		return unit;
	}

	@Override
	public ILogicalUnit getUnit(String unitName) {
		ILogicalUnit unit = logicalUnits.get(driveName);
		return unit;
	}
}
