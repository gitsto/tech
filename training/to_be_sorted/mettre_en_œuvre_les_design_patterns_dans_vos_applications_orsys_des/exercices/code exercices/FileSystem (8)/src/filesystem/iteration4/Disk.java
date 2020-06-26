package filesystem.iteration4;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Disk implements IDisk {
	private static IDisk disk;
	private IPhysicalDisk physicalDisk;
	private char driveName='A';
	private Map<String, ILogicalUnit> logicalUnits = new HashMap<String, ILogicalUnit>();
	
	private Disk(IPhysicalDisk physicalDisk) {
		this.physicalDisk = physicalDisk;
	}

	public static IDisk getInstance() {
		if (disk == null) {
			disk = new Disk(new MyPhysicalDisk());
		}
		return disk;
	}

	@Override
	public int getCapacity() {
		return physicalDisk.getCapacity();
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
		ILogicalUnit unit = logicalUnits.get(unitName);
		return unit;
	}
	
	public void format(ILogicalUnit unit, String formatName) {
		IFormat format = physicalDisk.getFormat(formatName);
		unit.format(format);
	}

}
