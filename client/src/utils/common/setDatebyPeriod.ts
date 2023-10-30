import moment from 'moment';

export const setDatebyPeriod = (selectedPeriod: number) => {
	const now = new Date();

	switch (selectedPeriod) {
		// 전체
		case 0: {
			return '2000-01-01';
		}
		// 1일
		case 1: {
			return moment(now).format('YYYY-MM-DD');
		}
		// 1주
		case 2: {
			const tmp = new Date(now.setDate(now.getDate() - 7));
			return moment(tmp).format('YYYY-MM-DD');
		}
		// 1개월
		case 3: {
			const tmp = new Date(now.setMonth(now.getMonth() - 1));
			return moment(tmp).format('YYYY-MM-DD');
		}
		// 3개월
		case 4: {
			const tmp = new Date(now.setMonth(now.getMonth() - 3));
			return moment(tmp).format('YYYY-MM-DD');
		}
		// 1년
		case 5: {
			const tmp = new Date(now.setFullYear(now.getFullYear() - 1));
			return moment(tmp).format('YYYY-MM-DD');
		}

		default:
			return moment(now).format('YYYY-MM-DD');
	}
};
