package filesystem.iteration6;

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
	public IPhysicalDisk getPhysicalDisk() {
		return physicalDisk;
	}

	@Override
	public int getCapacity() {
		return physicalDisk.getCapacity();
	}

	@Override
	public ILogicalUnit createUnit(int capacity) {
		String unitName = "" + driveName;
		physicalDisk.createPartition(unitName, capacity);
		ILogicalUnit unit = new LogicalUnit(unitName, capacity);
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
		IFormat format = createFormat(unit, formatName);
		unit.format(format);
	}

	@SuppressWarnings("unchecked")
	private IFormat createFormat(ILogicalUnit unit, String formatName) {
		try {
			Class<IFormat> cf = (Class<IFormat>) Class.forName("Format" + formatName);
			Constructor<IFormat> ctor = cf.getConstructor(IPhysicalDisk.class, ILogicalUnit.class);
			IFormat format = ctor.newInstance(physicalDisk, unit);
			return format;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
