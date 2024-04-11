[Setup]
AppName=integration_project_frontend-1.0-SNAPSHOT
AppVersion=1.0
DefaultDirName={pf}\integration_project_frontend-1.0-SNAPSHOT
DisableDirPage=yes
DisableProgramGroupPage=yes
OutputDir=Output

[Files]
Source: "C:\dev\git\repos\integration_project_frontend\target\*"; DestDir: "{app}"
Source: "C:\Program Files (x86)\Java\jre1.8.0_181\*"; DestDir: "{app}\jre"
Source: "C:\Users\yopai\OneDrive\Área de Trabalho\integration_project_install\icon.ico"; DestDir: "{app}"

[Icons]
Name: "{group}\integration_project_frontend-1.0-SNAPSHOT"; Filename: "{app}\integration_project_frontend-1.0-SNAPSHOT.jar"; IconFilename: "{app}\icon.ico"

[Run]
Filename: "{app}\jre\bin\java.exe"; Parameters: "-jar ""{app}\integration_project_frontend-1.0-SNAPSHOT.jar"""; Flags: nowait postinstall runhidden
