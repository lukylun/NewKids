export const getSpeech = (text: string): void => {
	let voices: SpeechSynthesisVoice[] = [];
	const setVoiceList = (): void => {
		voices = window.speechSynthesis.getVoices();
	};

	setVoiceList();

	if (window.speechSynthesis.onvoiceschanged !== undefined) {
		window.speechSynthesis.onvoiceschanged = setVoiceList;
	}

	const speech = (txt: string): void => {
		const lang: string = 'ko-KR';
		const utterThis: SpeechSynthesisUtterance = new SpeechSynthesisUtterance(txt);
		const rate: number = 0.9;

		utterThis.lang = lang;
		utterThis.rate = rate;

		const korVoice: SpeechSynthesisVoice | undefined = voices.find(
			(element) => element.lang === lang || element.lang === lang.replace('-', '_'),
		);

		if (korVoice) {
			utterThis.voice = korVoice;
		} else {
			return;
		}
		window.speechSynthesis.speak(utterThis);
	};

	speech(text);
};
