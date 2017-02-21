package com.accenture.fsadd.extif;

import java.time.LocalDateTime;

public interface ExtIfDataMapper {
	/**
	 * Map the extIf data to Domain data
	 * @param lastExecutedTime time that last extif was executed.
	 */
	void map(LocalDateTime lastExecutedTime);
}
