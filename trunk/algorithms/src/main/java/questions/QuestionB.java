package questions;

import java.util.Arrays;

public class QuestionB {
	public static int getMinimumTapeCount(final Batch batch) {
		int[] orderedFilesSizes = batch.getFileSizes();
		Arrays.sort(orderedFilesSizes);

		int numberOfTapes = 0;
		int tapeSize = batch.getTapeSize();
		int nonAllocatedSpacePerTape = tapeSize;
		int numberOfFilesPerTape = 0;
		// Bigger files first
		for (int i = orderedFilesSizes.length - 1; i >= 0; i--) {
			int aFileSize = orderedFilesSizes[i];
			if (nonAllocatedSpacePerTape - aFileSize >= 0) {
				nonAllocatedSpacePerTape -= aFileSize;
				numberOfFilesPerTape++;
				if (numberOfFilesPerTape == 2 || i == 0) {
					numberOfTapes++;
					nonAllocatedSpacePerTape = tapeSize;
					numberOfFilesPerTape = 0;
				} 
			} else {
				// Next tape, prior tape got just one element
				numberOfTapes++;
				nonAllocatedSpacePerTape = tapeSize - aFileSize;
				numberOfFilesPerTape = 1;
			}
		}

		return numberOfTapes;
	}
	
	public static void main(String[] args) {
		BatchTest aBatchTest = new BatchTest(100, new int[] {70, 70, 20, 80});
		System.out.println(getMinimumTapeCount(aBatchTest));
	}
	
	static class BatchTest implements Batch {
	    private int[] filesSizes;
	    private int tapeSize;
	    
	    public BatchTest(int tapeSize, int[] filesSizes) {
	    	this.tapeSize = tapeSize;
	    	this.filesSizes = filesSizes;
		}

		@Override
		public int[] getFileSizes() {
			return filesSizes;
		}

		@Override
		public int getTapeSize() {
			return tapeSize;
		}
		
	}
}
