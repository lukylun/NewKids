export const dateArrToString = (date: [number, number, number, number, number, number, number]) => {
	const addLength = (num: number): string => {
		if (`${num}`.length === 1) {
			return `${0}${num}`;
		}
		return `${num}`;
	};
	return `${addLength(date[0])}.${addLength(date[1])}.${addLength(date[2])} ${addLength(date[3])}:${addLength(
		date[4],
	)}:${addLength(date[5])}`;
};
