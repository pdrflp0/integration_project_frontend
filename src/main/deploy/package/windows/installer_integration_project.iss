[Setup]
AppName=integration_project_frontend-1.0-SNAPSHOT
AppVerName=integration_project_frontend-1.0-SNAPSHOT 1.0
AppVersion=1.0
AppId={{updt-sensor}}
DefaultDirName={localappdata}\integration_project_frontend-1.0-SNAPSHOT
DefaultGroupName=IntegrationProject
DisableReadyPage=Yes_
DisableDirPage=Yes
DisableWelcomePage=Yes
DisableStartupPrompt=Yes
DisableProgramGroupPage=Yes
OutputBaseFilename=Integration_Project-1.0-SNAPSHOT
Compression=lzma
SolidCompression=yes
SetupIconFile={app}\integration_project_frontend-1.0-SNAPSHOT.ico
UninstallDisplayIcon={app}\integration_project_frontend-1.0-SNAPSHOT.ico
UninstallDisplayName=Uninstall
WizardImageStretch=yes
WizardSmallImageFile=icon_integration_project.bmp

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "Additional tasks:"; Flags: unchecked

[Files]
Source: "integration_project_frontend-1.0-SNAPSHOT/integration_project_frontend-1.0-SNAPSHOT.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "integration_project_frontend-1.0-SNAPSHOT\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\integration_project_frontend-1.0-SNAPSHOT"; Filename: "{app}\integration_project_frontend-1.0-SNAPSHOT.exe"; WorkingDir: "{app}"; IconFilename: "{app}\integration_project_frontend-1.0-SNAPSHOT.ico"
Name: "{commondesktop}\integration_project_frontend-1.0-SNAPSHOT"; Filename: "{app}\integration_project_frontend-1.0-SNAPSHOT.exe"; WorkingDir: "{app}"; IconFilename: "{app}\integration_project_frontend-1.0-SNAPSHOT.ico"

[Run]
Filename: "{app}\integration_project_frontend-1.0-SNAPSHOT.exe"; Description: "{cm:LaunchProgram,integration_project_frontend-1.0-SNAPSHOT}"; Flags: nowait postinstall skipifsilent
Filename: "{app}\integration_project_frontend-1.0-SNAPSHOT.exe"; Parameters: "-install -svcName ""integration_project_frontend-1.0-SNAPSHOT"" -svcDesc ""integration_project_frontend-1.0-SNAPSHOT"" -mainExe ""integration_project_frontend-1.0-SNAPSHOT.exe"""

[UninstallRun]
Filename: "{app}\integration_project_frontend-1.0-SNAPSHOT.exe"; Parameters: "-uninstall"; Flags: runhidden