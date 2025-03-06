import pandas as pd
from openpyxl import load_workbook

path = "C:/Users/joajo/Downloads/Sales report_Comparative.xlsx"
filtreYearMonth = "2025.02"  # Changer la valeur ici pour filtrer un autre mois

# 📌 Liste des colonnes dans l'ordre souhaité
colonnes_finales = [
    "Sales Office Name (Soldto)", "Sales Group Name", "Profit Center Code", "Profit Center Name",
    "Sales Rep", "Customer", "Statistic Customer Name (Sold to)", "Statistic Customer Code (Sold To)",
    "Payer Name", "Payer Code", "Customer (Billto) Code", "Customer (Billto) Name",
    "Vessel Name", "Vessel Code", "IMO code", "Vessel type code", "Vessel Type Name",
    "Year/Month", "Delivery Port", "Country (Shipto)", "Invoice Num", "Date",
    "Division Name", "Order Num", "Year", "RIP Code", "Product name",
    "Packaging Code", "Material Code", "Material Name", "Price Group",
    "Net Weight (Tons)", "Volume (KL)", "Net Turnover w/o tax",
    "Gross margin on variable costs", "Pa Purchase costs of sales",
    "Pa Running variable costs", "Pa Marine Upstream Margin",
    "GMVE", "FFSO", "End Year Rebate", "COR", "COR-K$", "Profit Center Name 2"
]

# 🔹 **Charger toutes les feuilles du fichier**
xls = pd.ExcelFile(path, engine="openpyxl")
sheets = {sheet_name: xls.parse(sheet_name) for sheet_name in xls.sheet_names}

# 🔹 **Travailler uniquement sur la feuille "Results"**
df = sheets["Results"]

# 🔹 **Vérifier les colonnes existantes et les colonnes manquantes**
colonnes_existantes = list(df.columns)
colonnes_manquantes = [col for col in colonnes_finales if col not in colonnes_existantes]

# 🔹 **Ajouter les colonnes manquantes avec des valeurs vides (NaN)**
for col in colonnes_manquantes:
    df[col] = None  # `None` = équivalent de `NaN` en pandas

# 🔹 **Fixer le format de la colonne "Year/Month" (éviter la virgule)**
if "Year/Month" in df.columns:
    df["Year/Month"] = df["Year/Month"].astype(str)  # Convertir en texte

# 🔹 **Fixer le format de la colonne "Date" (éviter le format avec l'heure)**
if "Date" in df.columns:
    df["Date"] = pd.to_datetime(df["Date"], errors='coerce').dt.strftime("%d/%m/%Y")  # Convertir en JJ/MM/YYYY

# 🔹 **Réorganiser les colonnes selon la liste donnée**
df = df[colonnes_finales]

# 🔹 **Filtrer les données pour l'année/mois spécifique seulement si `filtreYearMonth` est défini**
if filtreYearMonth:
    df = df.dropna(subset=["Year/Month"])  # Supprimer les lignes avec Year/Month vide
    df = df[df["Year/Month"] == filtreYearMonth]

# 🔹 **Mapper les valeurs de "Profit Center Code" vers "Profit Center Name"**
profit_center_mapping = {
    "3F2MARVDIR": "Lub Marine Ventes Directes",
    "3F2MAREXAD": "Lub pour Marine Vtes Ind Export Concentrés",
    "3F2MAREXPF": "Lub Marine Vtes Ind Export Pdts Finis",
    "3F2MARINAV": "Lub Marine Ventes Indirectes Network"
}

# Appliquer la correspondance **uniquement aux lignes où "Profit Center Name" est vide**
df["Profit Center Name"] = df["Profit Center Name"].fillna(df["Profit Center Code"].map(profit_center_mapping))

# 🔹 **Mettre à jour uniquement la feuille "Results" sans supprimer les autres**
sheets["Results"] = df  # On met à jour les données modifiées

# 🔹 **Sauvegarder toutes les feuilles, mais en modifiant uniquement "Results"**
with pd.ExcelWriter(path, engine="openpyxl", mode="w") as writer:
    for sheet_name, data in sheets.items():
        data.to_excel(writer, sheet_name=sheet_name, index=False)  # Sauvegarde proprement chaque feuille

print(f"✅ Réorganisation et filtrage terminé ! {len(df)} lignes restantes après filtrage sur '{filtreYearMonth}'")
