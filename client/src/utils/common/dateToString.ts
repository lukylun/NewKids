import moment from 'moment';

export const dateToString = (date: Date, forSearch?: boolean): string => {
	if (forSearch) return moment(date).format('YYYY-MM-DD');
	return moment(date).format('YYYY-MM-DD HH:mm');
};
