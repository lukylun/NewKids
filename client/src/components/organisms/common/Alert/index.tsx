import React, { Dispatch, SetStateAction } from 'react';
import Swal from 'sweetalert2';
import Button from 'components/atoms/common/Button';
import { AlertContainer } from './style';

interface IAlertProps {
	setStage: Dispatch<SetStateAction<number>>;
	setNum: Dispatch<SetStateAction<number>>;
	imageUrls: string;
	imageHeights: number;
	titles: string;
	confirms: string;
	colors: string;
	num: number;
}

function Alert(props: IAlertProps) {
	const { setStage, setNum, imageUrls, imageHeights, titles, confirms, colors, num } = props;

	const alert = () => {
		Swal.fire({
			imageUrl: imageUrls,
			imageHeight: imageHeights,
			title: titles,
			confirmButtonColor: colors,
			confirmButtonText: confirms,
		}).then(() => {
			if (num === 0) {
				setNum(1);
				setStage(1);
			} else if (num === 1) {
				setNum(2);
				setStage(2);
			} else if (num === 2) {
				setNum(3);
				setStage(3);
			} else if (num === 3) {
				setNum(4);
				setStage(4);
			} else if (num === 4) {
				setStage(5);
			}
		});
	};

	return (
		<AlertContainer>
			<Button size="s" radius="m" color="Primary" text="제출" handleClick={alert} />
		</AlertContainer>
	);
}

export default Alert;
