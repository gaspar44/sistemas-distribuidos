program DATE_PROG {
	version DATE_VERS {
		long BIN_DATE(void)   = 1; /* Procedimiento 1 */
		string STR_DATE(long) = 2; /* Procedimiento 2 */
		long BIN_DATE_DIFFERENCE(long) = 3; /* Procedimiento 3 */
		string STR_DATE_DIFFERENCE(long) = 4; /* Procedimiento 4 */
	} = 1; /* Version 1 */
} = 600000000; /* Programa 600000000 */