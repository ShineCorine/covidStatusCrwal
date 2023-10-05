# covidStatusCrwal
웹페이지의 표 정보를 읽어와서 PDF(.pdf)와 엑셀(.xlsx) 파일로 저장하는 프로그램입니다.

# 기능 설명
## 1. 웹 페이지로부터 데이터를 가져오는 기능(크롤링 기능) 
jsoup 라이브러리를 이용해서 html에서 필요한 데이터를 가져옵니다.

한국의 [코로나 통계 홈페이지](https://ncov.kdca.go.kr/bdBoardList_Real.do)에서 

연령대 별 확진자/사망자/치명률 정보를 가져와서 

CovidStatusByAge 인스턴스에 연령대, 확진자, 확진률, 사망자, 사망률, 치명률 정보를 저장합니다.

이 정보들은 CovidStatusController라는 클래스가 관리하여 데이터들을 저장하거나 내보내는 기능을 가졌습니다.

## 2. 가져온 데이터를 PDF 파일로 저장하는 기능
PdfExporter 클래스는 CovidStatusByAge의 list와 파일 이름, 열 이름을 전달받아 PDF파일로 출력하는 기능을 가졌습니다.

iText7 8.0.1 라이브러리를 사용하여 PDF로 데이터를 출력하는 기능을 가졌습니다. 

## 3. 가져온 데이터를 엑셀 파일로 저장하는 기능.
ExcelExporter 클래스는 CovidStatusByAge List와 파일 이름, 열 이름을 전달받아 Excel 파일로 출력하는 기능을 가졌습니다.

xlsx 형식의 파일을 다루기 위해서 poi뿐만아니라 poi-ooxml  라이브러리를 사용하였습니다.




# 개발 도구
- 개발 언어: Java
- open jdk 11
- Editor: Intellij
