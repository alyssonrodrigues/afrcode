package br.com.afrcode.arquitetura.teste.unitario.util.time;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * CÓPIA de StopWatch para contagem de tempo em nanosegundos ao invés de
 * milissegundos.
 * 
 * 
 */
public class NanoStopWatch {
    /**
     * Identifier of this stop watch. Handy when we have output from multiple
     * stop watches and need to distinguish between them in log or console
     * output.
     */
    private final String id;

    private boolean keepTaskList = true;

    private final List<TaskInfo> taskList = new LinkedList<TaskInfo>();

    /** Start time of the current task */
    private long startTimeNanos;

    /** Is the stop watch currently running? */
    private boolean running;

    /** Name of the current task */
    private String currentTaskName;

    private TaskInfo lastTaskInfo;

    private int taskCount;

    /** Total running time */
    private long totalTimeNanos;

    /**
     * Construct a new stop watch. Does not start any task.
     */
    public NanoStopWatch() {
        this.id = "";
    }

    /**
     * Construct a new stop watch with the given id. Does not start any task.
     * 
     * @param id
     *            identifier for this stop watch. Handy when we have output from
     *            multiple stop watches and need to distinguish between them.
     */
    public NanoStopWatch(String id) {
        this.id = id;
    }

    /**
     * Determine whether the TaskInfo array is built over time. Set this to
     * "false" when using a StopWatch for millions of intervals, or the task
     * info structure will consume excessive memory. Default is "true".
     */
    public void setKeepTaskList(boolean keepTaskList) {
        this.keepTaskList = keepTaskList;
    }

    /**
     * Start an unnamed task. The results are undefined if stop() or timing
     * methods are called without invoking this method.
     * 
     * @see #stop()
     */
    public void start() {
        start("");
    }

    /**
     * Start a named task. The results are undefined if stop() or timing methods
     * are called without invoking this method.
     * 
     * @param taskName
     *            the name of the task to start
     * @see #stop()
     */
    public void start(String taskName) {
        if (this.running) {
            throw new IllegalStateException("Can't start StopWatch: it's already running");
        }
        this.startTimeNanos = System.nanoTime();
        this.running = true;
        this.currentTaskName = taskName;
    }

    /**
     * Stop the current task. The results are undefined if timing methods are
     * called without invoking at least one pair start() / stop() methods.
     * 
     * @see #start()
     */
    public void stop() {
        if (!this.running) {
            throw new IllegalStateException("Can't stop StopWatch: it's not running");
        }
        long lastTime = System.nanoTime() - this.startTimeNanos;
        this.totalTimeNanos += lastTime;
        this.lastTaskInfo = new TaskInfo(this.currentTaskName, lastTime);
        if (this.keepTaskList) {
            this.taskList.add(lastTaskInfo);
        }
        ++this.taskCount;
        this.running = false;
        this.currentTaskName = null;
    }

    /**
     * Return whether the stop watch is currently running.
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Return the time taken by the last task.
     */
    public long getLastTaskTimeNanos() {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task interval");
        }
        return this.lastTaskInfo.getTimeNanos();
    }

    /**
     * Return the name of the last task.
     */
    public String getLastTaskName() {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task name");
        }
        return this.lastTaskInfo.getTaskName();
    }

    /**
     * Return the last task as a TaskInfo object.
     */
    public TaskInfo getLastTaskInfo() {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task info");
        }
        return this.lastTaskInfo;
    }

    /**
     * Return the total time in nanoseconds for all tasks.
     */
    public long getTotalTimeNanos() {
        return this.totalTimeNanos;
    }

    /**
     * Return the total time in seconds for all tasks.
     */
    public double getTotalTimeSeconds() {
        final double oneSecondInNanos = 1000000000.0;
        return this.totalTimeNanos / oneSecondInNanos;
    }

    /**
     * Return the number of tasks timed.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Return an array of the data for tasks performed.
     */
    public TaskInfo[] getTaskInfo() {
        if (!this.keepTaskList) {
            throw new UnsupportedOperationException("Task info is not being kept!");
        }
        return this.taskList.toArray(new TaskInfo[this.taskList.size()]);
    }

    /**
     * Return a short description of the total running time.
     */
    public String shortSummary() {
        return "StopWatch '" + this.id + "': running time (nanos) = " + getTotalTimeNanos();
    }

    /**
     * Return a string with a table describing all tasks performed. For custom
     * reporting, call getTaskInfo() and use the task info directly.
     */
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder(shortSummary());
        sb.append('\n');
        if (this.keepTaskList) {
            sb.append("-----------------------------------------\n");
            sb.append("ns     ms     %     Task name\n");
            sb.append("-----------------------------------------\n");
            NumberFormat nanof = NumberFormat.getNumberInstance();
            final int maxIntegerDigits = 11;
            nanof.setMinimumIntegerDigits(maxIntegerDigits);
            nanof.setGroupingUsed(false);
            NumberFormat millisf = NumberFormat.getNumberInstance();
            final int minIntegerDigits = 5;
            millisf.setMinimumIntegerDigits(minIntegerDigits);
            final int minIntegerFractionDigits = 3;
            millisf.setMinimumFractionDigits(minIntegerFractionDigits);
            millisf.setGroupingUsed(false);
            NumberFormat pf = NumberFormat.getPercentInstance();
            pf.setMinimumIntegerDigits(minIntegerFractionDigits);
            pf.setGroupingUsed(false);
            for (TaskInfo task : getTaskInfo()) {
                sb.append(nanof.format(task.getTimeNanos())).append("  ");
                sb.append(millisf.format(task.getTimeMillis())).append("  ");
                sb.append(pf.format(task.getTimeSeconds() / getTotalTimeSeconds())).append("  ");
                sb.append(task.getTaskName()).append("\n");
            }
        } else {
            sb.append("No task info kept");
        }
        return sb.toString();
    }

    /**
     * Return an informative string describing all tasks performed For custom
     * reporting, call <code>getTaskInfo()</code> and use the task info
     * directly.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(shortSummary());
        if (this.keepTaskList) {
            for (TaskInfo task : getTaskInfo()) {
                sb.append("; [").append(task.getTaskName()).append("] took ").append(task.getTimeNanos());
                final double oneHundred = 100.0;
                long percent = Math.round((oneHundred * task.getTimeSeconds()) / getTotalTimeSeconds());
                sb.append(" = ").append(percent).append("%");
            }
        } else {
            sb.append("; no task info kept");
        }
        return sb.toString();
    }

    /**
     * Inner class to hold data about one task executed within the stop watch.
     */
    public static final class TaskInfo {

        private final String taskName;

        private final long timeNanos;

        TaskInfo(String taskName, long timeNanos) {
            this.taskName = taskName;
            this.timeNanos = timeNanos;
        }

        /**
         * Return the name of this task.
         */
        public String getTaskName() {
            return this.taskName;
        }

        /**
         * Return the time in nanoseconds this task took.
         */
        public long getTimeNanos() {
            return this.timeNanos;
        }

        /**
         * Return the time in milliseconds this task took.
         * 
         * @return
         */
        public double getTimeMillis() {
            final double oneMiliInNanos = 1000000.0;
            return this.timeNanos / oneMiliInNanos;
        }

        /**
         * Return the time in seconds this task took.
         */
        public double getTimeSeconds() {
            final double oneSecondInNanos = 1000000000.0;
            return this.timeNanos / oneSecondInNanos;
        }
    }

}
